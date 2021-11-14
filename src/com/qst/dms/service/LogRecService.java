package com.qst.dms.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.qst.dms.db.DBUtil;
import com.qst.dms.dos.MenuDriver;
import com.qst.dms.entity.DataBase;
import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.MatchedLogRec;

/**
 * @Author: Richie
 * @Date: 2021-07-07 15:33:54
 * @LastEditTime: 2021/07/12
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\service\LogRecService.java
 */

/**
 * 日志业务类，实现日志信息的采集及打印输出
 */
public class LogRecService {
    public Scanner scanner = new Scanner(System.in);

    /**
     * 实现日志信息的采集
     * 
     * @return
     * @throws Exception
     */
    public LogRec inputLog() throws Exception {

        // 提示用户输入ID标识
        System.out.println("请输入ID标识(仅能是一串无空格的数字串且小于等于9位)：");

        // 接收键盘输入的整数
        int id = -1;

        // 处理错误输入
        try {
            while (true) {
                id = MenuDriver.getUserChoice(0, 999999999);
                if (id >= 0 && id <= 999999999) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("输入id异常!");
        }

        Date nowDate = null;
        try {
            // 获取当前系统时间
            nowDate = new Date();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("nowDate异常!");
        }

        // 接收键盘输入的字符串信息
        String address = null;
        try {
            // 提示用户输入地址
            System.out.println("请输入地址：");
            address = scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("输入address异常!");
        }

        // 数据状态是“采集”
        int type = LogRec.GATHER;

        String user = null;
        try {
            // 提示用户输入登录用户名
            System.out.println("请输入登录用户名：");
            // 接收键盘输入的字符串信息
            user = scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("输入user异常!");
        }

        String ip = null;
        try {
            // 提示用户输入主机IP
            System.out.println("请输入主机IP:");
            // 接收键盘输入的字符串信息

            String validIpRegex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            while (true) {
                ip = scanner.nextLine();
                if (ip.matches(validIpRegex) == true)
                    break;
                System.out.println("请重新输入主机IP(*.*.*.*):");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("输入ip异常!");
        }

        int logType = -1;
        try {
            // 提示用户输入登录状态、登出状态
            System.out.println("请输入登录状态:1是登录，0是登出");
            while (true) {
                logType = MenuDriver.getUserChoice(0, 1);
                if (logType >= 0 && logType <= 1) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("输入logType异常!");
        }

        LogRec log = null;
        try {
            log = new LogRec(id, nowDate, address, type, user, ip, logType);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("创建log异常!");
        }
        return log;
    }

    /**
     * 输出日志信息
     * 
     * @param logRecs
     */
    public void showLog(LogRec... logRecs) {
        for (LogRec i : logRecs) {
            System.out.println(i.toString());
        }
    }

    public void showLog(List<LogRec> logRecs) {
        for (LogRec log : logRecs) {
            if (log != null) {
                System.out.println(log);
            }

        }
    }

    /**
     * 显示匹配日志信息
     * 
     * @param matchLogs
     */
    public void showMatchLog(MatchedLogRec... matchLogs) {
        if (matchLogs.length == 0 || matchLogs[0] == null) {
            System.out.println("无匹配记录");
        }
        for (MatchedLogRec i : matchLogs) {
            if (i != null) {
                System.out.println(i.toString());
            }
        }
    }

    public void showMatchLog(List<MatchedLogRec> matches) {
        if (matches.size() == 0 || matches.get(0) == null) {
            System.out.println("无匹配记录");
        }
        for (MatchedLogRec log : matches) {
            if (log != null)
                System.out.println(log);
        }
    }

    /**
     * 日志信息的匹配
     * 
     * @param logIn
     * @param logOut
     * @return
     */
    public boolean MatchLogRec(LogRec logIn, LogRec logOut) {
        if (logIn.getUser().equals(logOut.getUser()) && logIn.getIp().equals(logOut.getIp())
                && logIn.getLogType() == LogRec.LOG_IN && logOut.getType() == LogRec.LOG_OUT
                && logIn.getTime().before(logOut.getTime()) && logOut.getType() == DataBase.GATHER
                && logIn.getType() == DataBase.GATHER) {
            logOut.setType(DataBase.MATHCH);
            logIn.setType(DataBase.MATHCH);
            return true;
        }
        return false;
    }

    /**
     * 匹配日志信息保存，参数是集合
     * 
     * @param matches
     */
    public void saveMatchedLogRec(List<MatchedLogRec> matches) {

        // 创建一个ObjectOutputStream对象输出流，并连接文件输出流
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            // 以可追加的方式创建文件输出流，数据保存到MatchLogs.txt文件中
            fos = new FileOutputStream("matchedLogrec.txt", true);
            oos = new ObjectOutputStream(fos);
            for (MatchedLogRec logRec : matches) {
                oos.writeObject(logRec);
                oos.flush();
            }
            oos.writeObject(null);
            oos.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.print("保存成功");

    }

    /**
     * 读匹配日志信息保存，参数是集合
     * 
     * @return
     */
    public List<MatchedLogRec> readMatchedLogRec() {

        // 创建一个ObjectInputStream对象输入流，并连接文件输入流，读MatchLogs.txt文件中
        List<MatchedLogRec> datas = new ArrayList<MatchedLogRec>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream("matchedLogrec.txt");
            ois = new ObjectInputStream(fis);
            MatchedLogRec logrec = null;
            while ((logrec = (MatchedLogRec) ois.readObject()) != null) {
                datas.add(logrec);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.print("读取成功");
        return datas;
    }

    /**
     * 匹配日志信息保存到数据库，参数是集合
     * 
     * @param matchLogs
     */
    public void saveMatchLogToDB(List<MatchedLogRec> matchLogs) {
        DBUtil db = new DBUtil();
        try {
            // 获取数据库链接
            db.getConnection();

            for (MatchedLogRec matchedLogRec : matchLogs) {

                // 获取匹配的登录日志
                LogRec login = matchedLogRec.getLogin();
                // 获取匹配的登出日志
                LogRec logout = matchedLogRec.getLogout();

                // 保存匹配记录中的登录日志
                String sql = "insert into gather_logrec(id,time,address,type,username,ip,logtype) values(?,?,?,?,?,?,?)";

                Object[] param = new Object[] { login.getId(), login.getTime(), login.getAddress(), login.getType(),
                        login.getUser(), login.getIp(), login.getLogType()
                };
                db.executeUpdate(sql, param);

                // 保存匹配记录中的登出日志
                param = new Object[] { logout.getId(), logout.getTime(), logout.getAddress(), logout.getType(),
                        logout.getUser(), logout.getIp(), logout.getLogType()
                };
                db.executeUpdate(sql, param);

                // 保存匹配日志的ID
                sql = "insert into matched_logrec(loginid,logoutid)values(?,?)";
                param = new Object[] { login.getId(), logout.getId() };
                db.executeUpdate(sql, param);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("数据保存失败！");
        } finally {

            // 关闭数据库连接，释放资源
            db.closeAll();
        }
    }

    public ArrayList<MatchedLogRec> readMatchedLogFromDB() {

        DBUtil db = new DBUtil();
        ArrayList<MatchedLogRec> matchedLogRecs = new ArrayList<MatchedLogRec>();

        try {
            // 获取数据库链接
            db.getConnection();

            // 查询匹配的日志
            String sql = "select i.id,i.time,i.address,i.type,i.username,i.ip,i.logtype,"
                    + " o.id,o.time,o.address,o.type,o.username,o.ip,o.logtype"
                    + " from matched_logrec m,gather_logrec i,gather_logrec o"
                    + " where i.id=m.loginid and o.id=m.logoutid";

            ResultSet rs = db.executeQuery(sql, null);
            while (rs.next()) {

                // 获取登录记录
                LogRec login = new LogRec(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7));

                // 获取登出记录
                LogRec logout = new LogRec(rs.getInt(8), rs.getDate(9), rs.getString(10), rs.getInt(11),
                        rs.getString(12), rs.getString(13), rs.getInt(14));

                // 添加匹配登录信息到匹配集合
                MatchedLogRec matchedLog = new MatchedLogRec(login, logout);
                matchedLogRecs.add(matchedLog);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("数据读取失败！");
        } finally {
            // 关闭数据库连接，释放资源
            db.closeAll();
        }
        return matchedLogRecs;
    }

    /**
     * 获取数据库中的所有匹配的日志信息，返回一个ResultSet
     * @return
     */
	public ResultSet readLogResult() {		
        DBUtil db = new DBUtil();
        ResultSet rs=null;
        try {
            // 获取数据库链接
            Connection conn=db.getConnection();
            // 查询匹配的日志，设置ResultSet可以使用除了next()之外的方法操作结果集
            Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);//对于修改不敏感,设置为可修改类型的参数
            
            String sql = "SELECT * from gather_logrec";
            rs = st.executeQuery(sql);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
  }
}

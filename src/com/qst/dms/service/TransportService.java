
package com.qst.dms.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.mysql.jdbc.Statement;
import com.qst.dms.db.DBUtil;
import com.qst.dms.dos.MenuDriver;
import com.qst.dms.entity.DataBase;
import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.entity.Transport;

/**
 * @Author: Richie
 * @Date: 2021-07-07 19:33:49
 * @LastEditTime: 2021/07/12
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\service\TransportService.java
 */

/**
 * 创建物流业务类，实现物流信息的采集及打印输出
 */
public class TransportService {
    public Scanner scanner = new Scanner(System.in);

    /**
     * 控制台模拟接收数据，并返回物流数据
     * 
     * @return
     * @throws Exception
     */
    public Transport inputTransport() throws Exception {
        // 提示用户输入ID标识
        System.out.println("请输入ID标识(仅能是一串无空格的数字串且小于等于9位)：");
        // 接收键盘输入的整数
        int id = -1;
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

        // 获取当前系统时间
        Date nowDate = new Date();

        // 提示用户输入地址
        System.out.println("请输入地址：");
        // 接收键盘输入的字符串信息
        String address;
        while (true) {
            address = scanner.nextLine();
            if (address.length() > 0)
                break;
            System.out.println("输入地址不能为空，请重新输入：");
        }

        // 数据状态是“采集”
        int type = Transport.GATHER;

        // 提示用户输入登录用户名
        System.out.println("请输入货物经手人：");
        // 接收键盘输入的字符串信息
        String handler = scanner.nextLine();

        // 提示用户输入收货人
        System.out.println("请输入收货人:");
        // 接收键盘输入的字符串信息
        String reciver = "";
        reciver = scanner.nextLine();

        // 提示用于输入物流状态
        System.out.println("请输入物流状态：1发货中，2送货中，3已签收");
        int transportType;
        while (true) {
            transportType = MenuDriver.getUserChoice(1, 3);
            if (transportType >= 1 && transportType <= 3) {
                break;
            }
        }
        Transport transporttype = new Transport(id, nowDate, address, type, handler, reciver, transportType);
        return transporttype;
    }

    /**
     * 输出物流信息
     * 
     * @param transports
     */
    public void showTransport(Transport... transports) {
        for (Transport i : transports) {
            System.out.println(i.toString());
        }
    }

    public void showTransport(List<Transport> transports) {
        for (Transport transport : transports) {
            if (transport != null)
                System.out.println(transport);
        }
    }

    /**
     * 显示匹配日志信息
     * 
     * @param matchLogs
     */
    public void showMatchTransport(MatchedTransport... matchTransports) {
        if (matchTransports.length == 0 || matchTransports[0] == null) {
            System.out.println("无匹配记录");
        }
        for (MatchedTransport i : matchTransports) {
            if (i != null) {
                System.out.println(i.toString());
            }
        }
    }

    public void showMatchTransport(List<MatchedTransport> matches) {
        if (matches.size() == 0 || matches.get(0) == null) {
            System.out.println("无匹配记录");
        }
        for (MatchedTransport transport : matches) {
            if (transport != null)
                System.out.println(transport);
        }

    }

    public boolean MatchTransport(Transport send, Transport tran, Transport rec) {
        if (send.getReciver().equals(tran.getReciver()) && tran.getReciver().equals(rec.getReciver())
                && send.getType() != DataBase.MATHCH && tran.getType() != DataBase.MATHCH
                && rec.getType() != DataBase.MATHCH && (send.getTime().before(tran.getTime()))
                && (tran.getTime().before(rec.getTime()))) {
            send.setType(DataBase.MATHCH);
            tran.setType(DataBase.MATHCH);
            rec.setType(DataBase.MATHCH);
            return true;
        }
        return false;
    }

    /**
     * 匹配物流信息保存，参数是集合
     * 
     * @param matches
     */
    public void saveMatchedTransport(List<MatchedTransport> matches) {
        // 创建一个ObjectOutputStream对象输出流，并连接文件输出流
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            // 以可追加的方式创建文件输出流，数据保存到MatchedTransports.txt文件中
            fos = new FileOutputStream("matchedTransports.txt", true);
            oos = new ObjectOutputStream(fos);
            for (MatchedTransport tra : matches) {
                oos.writeObject(tra);
                oos.flush();
            }
            oos.writeObject(null);
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读匹配物流信息保存，参数是集合
     * 
     * @return
     */
    public List<MatchedTransport> readTransport() {
        List<MatchedTransport> datas = new ArrayList<MatchedTransport>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            // 创建一个ObjectInputStream对象输入流，并连接文件输入流，读MatchedTransports.txt文件中
            fis = new FileInputStream("matchedTransports.txt");
            ois = new ObjectInputStream(fis);
            MatchedTransport trans = null;
            while ((trans = (MatchedTransport) ois.readObject()) != null) {
                datas.add(trans);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return datas;
    }

    /**
     * 匹配日志信息保存到数据库，参数是集合
     * 
     * @param matchLogs
     */
    public void saveMatchTransportToDB(List<MatchedTransport> matchTransports) {
        DBUtil db = new DBUtil();
        try {
            // 获取数据库链接
            db.getConnection();

            for (MatchedTransport matchedMatchedTransport : matchTransports) {

                // 获取匹配的发货信息
                Transport send = matchedMatchedTransport.getSend();
                // 获取匹配的送货信息
                Transport trans = matchedMatchedTransport.getTrans();
                // 获取匹配的签收信息
                Transport receive = matchedMatchedTransport.getReceive();

                // 保存匹配记录中的发货信息
                String sql = "insert into gather_transport(id,time,address,type,handler,reciver,transporttype) values(?,?,?,?,?,?,?)";

                Object[] param = new Object[] { send.getId(), send.getTime(), send.getAddress(), send.getType(),
                        send.getHandler(), send.getReciver(), send.getTransportType() };
                db.executeUpdate(sql, param);

                // 保存匹配记录中的送货信息
                param = new Object[] { trans.getId(), trans.getTime(), trans.getAddress(), trans.getType(),
                    trans.getHandler(), trans.getReciver(), trans.getTransportType() };
                db.executeUpdate(sql, param);

                // 保存匹配记录中的签收信息
                param = new Object[] { receive.getId(), receive.getTime(), receive.getAddress(), receive.getType(),
                    receive.getHandler(), receive.getReciver(), receive.getTransportType() };
                db.executeUpdate(sql, param);

                // 保存匹配日志的ID
                sql = "insert into matched_transport(sendid,transid,receiveid)values(?,?,?)";
                param = new Object[] { send.getId(),trans.getId(),receive.getId()};
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

    public ArrayList<MatchedTransport> readMatchedTransportFromDB() {

        DBUtil db = new DBUtil();
        ArrayList<MatchedTransport> matchedMatchedTransports = new ArrayList<MatchedTransport>();

        try {
            // 获取数据库链接
            db.getConnection();

            // 查询匹配的日志
            String sql = "select s.id,s.time,s.address,s.type,s.handler,s.reciver,s.transporttype,"
                    + " t.id,t.time,t.address,t.type,t.handler,t.reciver,t.transporttype,"
                    + " r.id,t.time,r.address,r.type,r.handler,r.reciver,r.transporttype"
                    + " from matched_Transport m,gather_Transport s,gather_Transport t,gather_Transport r"
                    + " where s.id=m.sendid and t.id=m.transid and r.id= m.receiveid";

            ResultSet rs = db.executeQuery(sql, null);
            while (rs.next()) {

                // 获取发货记录
                Transport send = new Transport(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7));

                // 获取送货记录
                Transport trans = new Transport(rs.getInt(8), rs.getDate(9), rs.getString(10), rs.getInt(11),
                        rs.getString(12), rs.getString(13), rs.getInt(14));
                //获取签收信息
                Transport receive = new Transport(rs.getInt(15), rs.getDate(16), rs.getString(17), rs.getInt(18),
                        rs.getString(19), rs.getString(20), rs.getInt(21));

                // 添加匹配登录信息到匹配集合
                MatchedTransport matchedTransport = new MatchedTransport(send, trans, receive);
                matchedMatchedTransports.add(matchedTransport);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("数据读取失败！");
        } finally {
            // 关闭数据库连接，释放资源
            db.closeAll();
        }
        return matchedMatchedTransports;
    }
    
    /**
     * 获取数据库中的所有匹配的物流信息，返回一个ResultSet
     * @return
     */
	public ResultSet readTransResult() {
		DBUtil db = new DBUtil();
		ResultSet rs = null;
		try {
			// 获取数据库链接
			Connection conn = db.getConnection();
			// // 查询匹配的物流，设置ResultSet可以使用除了next()之外的方法操作结果集
			Statement st = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

			String sql = "SELECT * from gather_transport";
			rs = st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}

package com.qst.dms.gather;

import java.util.ArrayList;
import java.util.List;

import com.qst.dms.entity.DataBase;
import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.util.DataAnalyseException;

/**
 * @Author: Richie
 * @Date: 2021-07-11 10:20:19
 * @LastEditTime: 2021/07/15
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\gather\LogRecAnalyse.java
 */

/**
 * 编写日志数据分析类,继承AbstractDataFilter抽象类，实现数据分析IDataAnalyse接口
 */
public class LogRecAnalyse extends AbstractDataFilter implements IDataAnalyse {

    /**
     * "登录"集合
     */
    private ArrayList<LogRec> logIns;
    /**
     * "登出"集合
     */
    private ArrayList<LogRec> logOuts;

    /**
     * 空构造方法
     */
    public LogRecAnalyse() {
    }

    /**
     * 带参构造方法
     * 
     * @param datas
     */
    public LogRecAnalyse(List<LogRec> datas) {
        super(datas);
    }

    /**
     * 带参构造方法
     * 
     * @param logIns
     * @param logOuts
     */
    public LogRecAnalyse(ArrayList<LogRec> logIns, ArrayList<LogRec> logOuts) {
        this.logIns = logIns;
        this.logOuts = logOuts;
    }

    /**
     * 带参构造方法
     * 
     * @param datas
     * @param logIns
     * @param logOuts
     */
    public LogRecAnalyse(List<? extends DataBase> datas, ArrayList<LogRec> logIns, ArrayList<LogRec> logOuts) {
        super(datas);
        this.logIns = logIns;
        this.logOuts = logOuts;
    }

    /**
     * 编写日志数据分析类
     */
    @Override
    public void doFilter() {

        // 获取数据集合
        @SuppressWarnings("unchecked")
        List<LogRec> logs = (List<LogRec>) this.getDatas();

        // 创建登录、登出数组
        logIns = new ArrayList<LogRec>();
        logOuts = new ArrayList<LogRec>();

        // 遍历，对日志数据进行过滤，根据日志登录状态分别放在不同的数组中
        for (LogRec log : logs) {
            if (log.getLogType() == LogRec.LOG_IN) {
                logIns.add(log);
            } else if (log.getLogType() == LogRec.LOG_OUT) {
                logOuts.add(log);
            }
        }
    }

    /**
     * 实现IDataAnalyse接口中数据分析方法
     */
    @Override
    public ArrayList<MatchedLogRec> matchData() {

        // 创建日志匹配数组
        ArrayList<MatchedLogRec> matchlogs = new ArrayList<MatchedLogRec>();

        // 数据匹配分析
        for (LogRec in : logIns) {
            for (LogRec out : logOuts) {
                if ((in.getUser().equals(out.getUser())) && (in.getIp().equals(out.getIp()))
                        && out.getType() == DataBase.GATHER && in.getType() == DataBase.GATHER) {

                    // 修改in和out日志状态类型为"匹配"
                    in.setType(DataBase.MATHCH);
                    out.setType(DataBase.MATHCH);

                    // 添加到匹配数组中
                    matchlogs.add(new MatchedLogRec(in, out));
                }
            }
        }

        try {
            if (matchlogs.size() == 0) {
                throw new DataAnalyseException("没有匹配数据");
            }
        } catch (DataAnalyseException e) {
            System.out.println(e.getMessage());
        }
        return matchlogs;
    }
}
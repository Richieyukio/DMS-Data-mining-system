package com.qst.dms.gather;

import java.util.ArrayList;
import java.util.List;

import com.qst.dms.entity.DataBase;
import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.entity.Transport;
import com.qst.dms.util.DataAnalyseException;

/**
 * @Author: Richie
 * @Date: 2021/07/12
 * @LastEditTime: 2021/07/21
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\gather\TransportAnalyse.java
 */

/**
 * 物流数据分析类,继承AbstractDataFilter抽象类,实现数据分析IDataAnalyse接口
 */
public class TransportAnalyse extends AbstractDataFilter implements IDataAnalyse {

    /**
     * 发货集合
     */
    private ArrayList<Transport> transSends;

    /**
     * 送货集合
     */
    private ArrayList<Transport> transIngs;

    /**
     * 已签收集合
     */
    private ArrayList<Transport> transRecs;

    /**
     * 空构造方法
     */
    public TransportAnalyse() {
    }

    /**
     * 带参构造方法
     * 
     * @param trans
     */
    public TransportAnalyse(List<Transport> trans) {
        super(trans);
    }

    /**
     * 带参构造方法
     * 
     * @param transSends
     * @param transIngs
     * @param transRecs
     */
    public TransportAnalyse(ArrayList<Transport> transSends, ArrayList<Transport> transIngs,
            ArrayList<Transport> transRecs) {
        this.transSends = transSends;
        this.transIngs = transIngs;
        this.transRecs = transRecs;
    }

    /**
     * 带参构造方法
     * 
     * @param datas
     * @param transSends
     * @param transIngs
     * @param transRecs
     */
    public TransportAnalyse(List<? extends DataBase> datas, ArrayList<Transport> transSends,
            ArrayList<Transport> transIngs, ArrayList<Transport> transRecs) {
        super(datas);
        this.transSends = transSends;
        this.transIngs = transIngs;
        this.transRecs = transRecs;
    }

    /**
     * 实现AbstractDataFilter抽象类中的过滤抽象方法
     */
    @Override
    public void doFilter() {

        // 获取数据集合
        @SuppressWarnings("unchecked")
        List<Transport> trans = (List<Transport>) this.getDatas();

        // 创建不同状态的物流数组
        transSends = new ArrayList<Transport>();
        transIngs = new ArrayList<Transport>();
        transRecs = new ArrayList<Transport>();

        // 遍历，对物流数据进行过滤，根据物流状态分别放在不同的数组中
        for (Transport transp : trans) {
            if (transp.getTransportType() == Transport.SENDDING)
                transSends.add(transp);
            else if (transp.getTransportType() == Transport.TRANSPORTING)
                transIngs.add(transp);
            else if (transp.getTransportType() == Transport.RECIEVED)
                transRecs.add(transp);
        }
    }

    /**
     * 实现IDataAnalyse接口中数据分析方法
     */
    @Override
    public ArrayList<MatchedTransport> matchData() {

        // 创建物流匹配数组
        ArrayList<MatchedTransport> matchTrans = new ArrayList<MatchedTransport>();

        // 数据匹配分析
        for (Transport send : transSends) {
            for (Transport tran : transIngs) {
                for (Transport rec : transRecs) {
                    if ((send.getReciver().equals(tran.getReciver())) && (send.getReciver().equals(rec.getReciver()))
                            && (tran.getType() != DataBase.MATHCH) && (rec.getType() != DataBase.MATHCH)
                            && (send.getType() != DataBase.MATHCH) && (send.getTime().before(tran.getTime()))
                            && (tran.getTime().before(rec.getTime()))) {

                        // 修改物流状态类型为“匹配”
                        send.setType(DataBase.MATHCH);
                        tran.setType(DataBase.MATHCH);
                        rec.setType(DataBase.MATHCH);

                        // 添加到匹配数组中
                        matchTrans.add(new MatchedTransport(send, tran, rec));
                    }
                }
            }
        }
        try {
            if (matchTrans.size() == 0) {
                throw new DataAnalyseException("没有匹配数据");
            }
        } catch (DataAnalyseException e) {
            System.out.println(e.getMessage());
        }
        return matchTrans;
    }
}
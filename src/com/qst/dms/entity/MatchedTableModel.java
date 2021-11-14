package com.qst.dms.entity;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.table.AbstractTableModel;

/**
 * @Author: Richie
 * @Date: 2021/07/19
 * @LastEditTime: 2021/07/19
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\entity\MatchedTableModel.java
 */

public class MatchedTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 使用ResultSet来创建TableModel
	public String[] value1 = new String[] { "登入", "登出" };
	public String[] value2 = new String[] { "发货中", "送货中", "已签收" };
	public Object[] object = new Object[] { "登入", "登出", "发货中", "送货中", "已签收" };
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	// 标志位，区分日志和物流：1，日志；0，物流
	private int sign;

	public MatchedTableModel(ResultSet rs, int sign) {
		this.rs = rs;
		this.sign = sign;
		try {
			rsmd = rs.getMetaData();
		} catch (Exception e) {
			rsmd = null;
		}
	}

	// 获取表格的行数
	public int getRowCount() {
		try {
			rs.last();
			// System.out.println(count);
			return rs.getRow();
		} catch (Exception e) {
			return 0;
		}
	}

	// 获取表格的列数
	public int getColumnCount() {
		try {
			// System.out.println(rsmd.getColumnCount());
			return rsmd.getColumnCount();
		} catch (Exception e) {
			return 0;
		}
	}

	// 获取指定位置的值
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			rs.absolute(rowIndex + 1);
			// return rs.getObject(columnIndex + 1);
			if (sign == 1) {
				if (columnIndex == 6) {
					if (rs.getObject(columnIndex + 1).equals(1)) {
						return value1[0];
					} else if (rs.getObject(columnIndex + 1).equals(0)) {
						return value1[1];
					}

				}
			} else if (sign == 0) {
				if (columnIndex == 6) {
					if (rs.getObject(columnIndex + 1).equals(1)) {
						return value2[0];
					} else if (rs.getObject(columnIndex + 1).equals(2)) {
						return value2[1];
					} else if (rs.getObject(columnIndex + 1).equals(3)) {
						return value2[2];
					}
				}
			}
			return rs.getObject(columnIndex + 1);
		} catch (Exception e) {
			return null;
		}
	}

	// 获取指定位置的值
//	public Object getValueAt(int rowIndex, int columnIndex) {
//		Object o = null;
//		try {
//			rs.absolute(rowIndex + 1);
//			if (sign == 1)
//				o = logsearch(rowIndex, columnIndex);
//			else
//				o = transsearch(rowIndex, columnIndex);
//			return rs.getObject(columnIndex + 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return o;
//	}
//
//	public Object logsearch(int rowIndex, int columnIndex) throws SQLException {
//		Object o = rs.getObject(columnIndex + 1);
//		if (columnIndex == 6) {
//			Integer i = (Integer) o;
//			return value1[i];
//		}
//		return o;
//	}
//
//	public Object transsearch(int rowIndex, int columnIndex) throws SQLException {
//		Object o = rs.getObject(columnIndex + 1);
//		if (columnIndex == 6) {
//			Integer i = (Integer) o;
//			return value2[i - 1];
//		}
//		return o;
//	}

	// 获取表头信息
	public String getColumnName(int column) {
		String[] logArray = { "日志ID", "采集时间", "采集地点", "状态", "用户名", "IP", "日志类型" };
		String[] tranArray = { "物流ID", "采集时间", "目的地", "状态", "经手人", "收货人", "物流类型" };
		return sign == 1 ? logArray[column] : tranArray[column];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

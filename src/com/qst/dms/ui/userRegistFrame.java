package com.qst.dms.ui;

/**
 * @Author: Richie
 * @Date: 2021/07/16
 * @LastEditTime: 2021/07/18
 * @LastEditors: Richie
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

public class userRegistFrame extends JFrame {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * 用户名文本框
	 */
	private JTextField textUserName;

	/**
	 * 用户联系方式文本框
	 */
	private JTextField textUserPhone;

	/**
	 * 密码文本框
	 */
	private JPasswordField passwordField;

	/**
	 * 确认密码文本框
	 */
	private JPasswordField rePasswordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userRegistFrame frame = new userRegistFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public userRegistFrame() {
		// set frame
		InitGlobalFont(new Font("楷体", Font.PLAIN, 16));
		setBackground(UIManager.getColor("Button.background"));
		setResizable(false);
		setTitle("用户注册界面");
		//UIManager.put("TitledBorder.font",new Font("楷体",Font.PLAIN,10));
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\images\\dms.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 350);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 用户名
		JLabel lblUserName = new JLabel("用      户      名：");
		//lblUserName.setFont(new Font("华文楷体", Font.PLAIN, 14));

		// 密码
		JLabel lblUserPwd = new JLabel("密                码：");
		//lblUserPwd.setFont(new Font("华文楷体", Font.PLAIN, 14));

		// 确认密码
		JLabel lblReUserPwd = new JLabel("确   认   密   码：");
		//lblReUserPwd.setFont(new Font("华文楷体", Font.PLAIN, 14));

		// 性别
		JLabel lblUserSex = new JLabel("性                别：");
		//lblUserSex.setFont(new Font("华文楷体", Font.PLAIN, 14));

		// 联系方式
		JLabel lblUserPhone = new JLabel("联系方式(电话)：");
		//lblUserPhone.setFont(new Font("华文楷体", Font.PLAIN, 14));

		// 用户名文本框
		textUserName = new JTextField();
		textUserName.setColumns(10);

		// 密码文本框
		passwordField = new JPasswordField();

		// 确认密码文本框
		rePasswordField = new JPasswordField();

		// 性别 男
		JRadioButton maleCheckBox = new JRadioButton("男");
		//maleCheckBox.setFont(new Font("华文楷体", Font.PLAIN, 14));

		// 性别 女
		JRadioButton fmaleCheckBox = new JRadioButton("女");
		//fmaleCheckBox.setFont(new Font("华文楷体", Font.PLAIN, 14));

		// 实现男女单选
		ButtonGroup sexGroup = new ButtonGroup();
		sexGroup.add(maleCheckBox);
		sexGroup.add(fmaleCheckBox);

		// 联系方式文本框
		textUserPhone = new JTextField();
		textUserPhone.setColumns(10);

		// 注册按钮
		JButton btnLoginButton = new JButton("注册");
		btnLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 获得用户名
				String userName = textUserName.getText();
				// 获得密码
				String userPwd = new String(passwordField.getPassword());
				// 获得重新输入的密码
				String userRePwd = new String(rePasswordField.getPassword());
				// 获得联系方式
				String userPhone = textUserPhone.getText();

				if ("".equals(userName)) {
					JOptionPane.showMessageDialog(null, "用户名不能为空", "输入错误", JOptionPane.ERROR_MESSAGE);
				} else if ("".equals(userPwd)) {
					JOptionPane.showMessageDialog(null, "密码不能为空", "输入错误", JOptionPane.ERROR_MESSAGE);
				} else if ("".equals(userRePwd)) {
					JOptionPane.showMessageDialog(null, "确认密码不能为空", "输入错误", JOptionPane.ERROR_MESSAGE);
				} else if (!userRePwd.equals(userPwd)) {
					JOptionPane.showMessageDialog(null, "确认密码与密码不匹配", "输入错误", JOptionPane.ERROR_MESSAGE);
				} else if (maleCheckBox.isSelected() == false && fmaleCheckBox.isSelected() == false) {
					JOptionPane.showMessageDialog(null, "为选择男女性别", "输入错误", JOptionPane.ERROR_MESSAGE);
				} else if (!checkCellphone(userPhone) && !checkTelephone(userPhone)) {
					JOptionPane.showMessageDialog(null, "输入的电话号码错误", "输入错误", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "登录成功，欢迎使用！", "友情提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		//btnLoginButton.setFont(new Font("华文楷体", Font.PLAIN, 14));

		// 关闭按钮
		JButton btnCloseButton = new JButton("关闭");
		btnCloseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 关闭
				dispose();
			}
		});
		//btnCloseButton.setFont(new Font("华文楷体", Font.PLAIN, 14));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap(19, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblUserSex)
						.addComponent(lblReUserPwd).addComponent(lblUserPwd).addComponent(lblUserName)
						.addComponent(btnLoginButton).addComponent(lblUserPhone))
				.addGap(43)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textUserPhone, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
						.addComponent(rePasswordField, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
						.addComponent(textUserName, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(maleCheckBox).addGap(31)
								.addComponent(fmaleCheckBox))
						.addComponent(btnCloseButton))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(35)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblUserName)
						.addComponent(textUserName, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(lblUserPwd).addComponent(passwordField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblReUserPwd)
						.addComponent(rePasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblUserSex)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(maleCheckBox)
								.addComponent(fmaleCheckBox)))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textUserPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUserPhone))
				.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnLoginButton)
						.addComponent(btnCloseButton))
				.addGap(18)));
		contentPane.setLayout(gl_contentPane);
	}

	private void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}

	}

	/**
	 * 验证手机号码
	 * 
	 * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
	 * 联通号码段:130、131、132、136、185、186、145 电信号码段:133、153、180、189
	 * 
	 * @param cellphone
	 * @return
	 */
	public static boolean checkCellphone(String cellphone) {
		String regex = "^((13[0-9])|(14[5-9])|(15([0-3]|[5-9]))|(16[6-7])|(17[1-8])|(18[0-9])|(19[1|3])|(19[5|6])|(19[8|9]))\\d{8}$";
		return cellphone.matches(regex);
	}

	/**
	 * 验证固话号码
	 * 
	 * @param telephone
	 * @return
	 */
	public static boolean checkTelephone(String telephone) {
		String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
		return telephone.matches(regex);
	}
}

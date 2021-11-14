package com.qst.dms.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;
import java.awt.SystemColor;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import com.qst.dms.entity.User;
import com.qst.dms.service.UserService;

/**
 * @Author: Richie
 * @Date: 2021/07/17
 * @LastEditTime: 2021/07/17
 * @LastEditors: Richie
 */

/**
 * 创建用户注册窗口,并将用户注册信息保存到数据库
 */
public class RegistFrame extends JFrame {

    /**
     * UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主面板
     */
    private JPanel contentPane;

    /**
     * 标签
     */
    private JLabel lblUserName, lblUserPwd, lblUserRePwd, lblUserSex, lblUserHobby, lblUserAdress, lblUserDegree,
            lblUserNameError, lblUserNameRight, lblUserPwdError, lblUserPwdRight, lblUserRePwdError, lblUserRePwdRight;

    /**
     * 用户名，文本框
     */
    private JTextField txtUserName;

    /**
     * 密码和确认密码，密码框
     */
    private JPasswordField txtUserPwd, txtUserRePwd;

    /**
     * 性别，单选按钮
     */
    private JRadioButton rbMale, rbFemale;

    /**
     * 爱好，多选框
     */
    private JCheckBox ckbRead, ckbNet, ckbSwim, ckbTour;

    /**
     * 地址，文本域
     */
    private JTextArea txtUserAdress;

    /**
     * 学历，组合框
     */
    private JComboBox<String> cmbUserDegree;

    /**
     * 确认和重置按钮
     */
    private JButton btnOk, resetButton, returnLoginButton;

    /**
     * 注册的用户
     */
    private static User user;

    /**
     * 用户业务类
     */
    private UserService userService;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        new RegistFrame("用户注册");
    }

    /**
     * 空构造方法
     */
    public RegistFrame() {
    }

    /**
     * 构造方法
     */
    public RegistFrame(String title) {
        super(title);

        // 设置全局字体
        InitGlobalFont(new Font("楷体", Font.PLAIN, 23));

        // 实例化用户业务类对象
        userService = new UserService();

        // 设置窗体的icon
        ImageIcon icon = new ImageIcon("images\\dms.png");
        this.setIconImage(icon.getImage());

        // 实例化组件
        contentPane = new JPanel();

        // 使用null布局
        contentPane.setLayout(null);

        // 实例化组件
        lblUserName = new JLabel("用 户 名：");
        lblUserName.setBounds(50, 10, 150, 60);

        lblUserPwd = new JLabel("密    码：");
        lblUserPwd.setBounds(50, 70, 150, 60);

        lblUserRePwd = new JLabel("确认密码：");
        lblUserRePwd.setBounds(50, 130, 150, 60);

        lblUserSex = new JLabel("性    别：");
        lblUserSex.setBounds(50, 190, 150, 60);

        lblUserHobby = new JLabel("爱    好：");
        lblUserHobby.setBounds(50,250, 150, 60);

        lblUserAdress = new JLabel("地    址：");
        lblUserAdress.setBounds(50, 310, 150, 60);

        lblUserDegree = new JLabel("学    历：");
        lblUserDegree.setBounds(50, 380, 150, 60);

        txtUserName = new JTextField(16);
        txtUserName.setBounds(165, 27, 190, 30);
        txtUserName.getDocument().addDocumentListener(new UserNameListener());

        txtUserPwd = new JPasswordField(16);
        txtUserPwd.setBounds(165, 87, 190, 30);
        txtUserPwd.setEchoChar('*');
        txtUserPwd.getDocument().addDocumentListener(new PasswordListener());

        txtUserRePwd = new JPasswordField(16);
        txtUserRePwd.setBounds(165, 147, 190, 30);
        txtUserRePwd.setEchoChar('*');
        txtUserRePwd.getDocument().addDocumentListener(new RePasswordListener());

        rbMale = new JRadioButton("男");
        rbMale.setBounds(180, 200, 60, 40);
        rbFemale = new JRadioButton("女");
        rbFemale.setBounds(280, 200, 60, 40);

        lblUserNameError = new JLabel("该用户名已被注册");
        lblUserNameError.setBounds(165, 50, 190, 40);
        lblUserNameError.setForeground(SystemColor.control);
        lblUserNameError.setFont(new Font("楷体", Font.PLAIN, 20));

        lblUserNameRight = new JLabel("\u221A");
        lblUserNameRight.setForeground(SystemColor.control);
        lblUserNameRight.setBounds(352, 20, 40, 40);
        lblUserNameRight.setFont(new Font("楷体", Font.PLAIN, 33));

        lblUserPwdError = new JLabel("密码不能小于6位");
        lblUserPwdError.setForeground(SystemColor.control);
        lblUserPwdError.setBounds(165, 110, 190, 40);
        lblUserPwdError.setFont(new Font("楷体", Font.PLAIN, 20));

        lblUserPwdRight = new JLabel("\u221A");
        lblUserPwdRight.setForeground(SystemColor.control);
        lblUserPwdRight.setBounds(352, 80, 190, 40);
        lblUserPwdRight.setFont(new Font("楷体", Font.PLAIN, 33));

        lblUserRePwdError = new JLabel("确认密码与密码不匹配");
        lblUserRePwdError.setForeground(SystemColor.control);
        lblUserRePwdError.setBounds(165, 168, 200, 40);
        lblUserRePwdError.setFont(new Font("楷体", Font.PLAIN, 20));

        lblUserRePwdRight = new JLabel("\u221A");
        lblUserRePwdRight.setForeground(SystemColor.control);
        lblUserRePwdRight.setBounds(352, 140, 190, 40);
        lblUserRePwdRight.setFont(new Font("楷体", Font.PLAIN, 33));

        // 性别的单选逻辑
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMale);
        bg.add(rbFemale);

        // 爱好，多选框实例化
        ckbRead = new JCheckBox("阅读");
        ckbRead.setBounds(170, 238, 80, 40);
        ckbNet = new JCheckBox("上网");
        ckbNet.setBounds(270, 238, 80, 40);
        ckbSwim = new JCheckBox("游泳");
        ckbSwim.setBounds(170, 280, 80, 40);
        ckbTour = new JCheckBox("旅游");
        ckbTour.setBounds(270, 280, 80, 40);

        // 地址
        txtUserAdress = new JTextArea(3, 20);
        txtUserAdress.setFont(new Font("楷体", Font.PLAIN, 17));
        txtUserAdress.setLineWrap(true);
        txtUserAdress.setWrapStyleWord(true);
        JScrollPane jsp = new JScrollPane(txtUserAdress);
        Dimension size = txtUserAdress.getPreferredSize();
        jsp.setBounds(175, 320, size.width, size.height);

        // 组合框显示的学历数组,设置组合框可编辑
        String str[] = { "小学", "初中", "高中", "本科", "硕士", "博士" };
        cmbUserDegree = new JComboBox<String>(str);
        cmbUserDegree.setBounds(175, 395, 90, 35);
        cmbUserDegree.setEditable(true);

        // 注册监听器，监听确定按钮
        btnOk = new JButton("确定");
        btnOk.setBounds(50, 450, 80, 45);
        btnOk.addActionListener(new RegisterListener());

        // 注册监听器，监听重置按钮
        resetButton = new JButton("重置");
        resetButton.setBounds(170, 450, 80, 45);
        resetButton.addActionListener(new ResetListener());

        // 注册监听器，监听返回按钮
        returnLoginButton = new JButton("返回");
        returnLoginButton.setBounds(290, 449, 80, 45);
        returnLoginButton.addActionListener(new returnLoginListener());

        // 将组件分组放入面板，然后将小面板放入主面板
        contentPane.add(lblUserName);
        contentPane.add(txtUserName);
        contentPane.add(lblUserPwd);
        contentPane.add(txtUserPwd);
        contentPane.add(lblUserRePwd);
        contentPane.add(txtUserRePwd);
        contentPane.add(lblUserNameError);
        contentPane.add(lblUserNameRight);
        contentPane.add(lblUserPwdError);
        contentPane.add(lblUserPwdRight);
        contentPane.add(lblUserRePwdError);
        contentPane.add(lblUserRePwdRight);
        contentPane.add(lblUserSex);
        contentPane.add(rbMale);
        contentPane.add(rbFemale);
        contentPane.add(lblUserHobby);
        contentPane.add(ckbRead);
        contentPane.add(ckbNet);
        contentPane.add(ckbSwim);
        contentPane.add(ckbTour);
        contentPane.add(lblUserAdress);
        contentPane.add(jsp);
        contentPane.add(lblUserDegree);
        contentPane.add(cmbUserDegree);
        contentPane.add(btnOk);
        contentPane.add(resetButton);
        contentPane.add(returnLoginButton);

        // 主面板放入窗体中
        this.add(contentPane);
        // 设置窗体大小
        this.setSize(430, 570);
        // 设置窗口在屏幕中央
        this.setLocationRelativeTo(null);
        // 设置窗体不能改变大小
        this.setResizable(false);
        // 设置窗体初始可见
        this.setVisible(true);

        //是否关闭
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int flag = JOptionPane.showConfirmDialog(null, "是否确认退出系统?", "提示!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (JOptionPane.YES_OPTION == flag) {
                    System.exit(0);
                } else {
                    return;
                }
            }
        });
    }

    /**
     * 设置全局字体
     * @param font
     */
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
     * 监听类，负责处理用户名输入文本框
     */
    public class UserNameListener implements DocumentListener {
        @Override
        public void changedUpdate(DocumentEvent e) {
            // 获取用户名
            String userName = txtUserName.getText().trim();
            lblUserNameError.setText("该用户名已被注册");
            // 进行判断
            if (userService.findUserByName(userName) != null) {
                // 提示信息改成red
                lblUserNameError.setForeground(SystemColor.RED);
                // 对勾改成control颜色
                lblUserNameRight.setForeground(SystemColor.control);
            } else if(!userName.equals("")){
                // 提示信息隐藏
                lblUserNameError.setForeground(SystemColor.control);
                // 对勾显示
                lblUserNameRight.setForeground(SystemColor.GREEN);
            } else {
                // 提示信息改成red
                lblUserNameError.setForeground(SystemColor.RED);
                lblUserNameError.setText("用户名不能为空");
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            // 获取用户名
            String userName = txtUserName.getText().trim();
            lblUserNameError.setText("该用户名已被注册");
            // 进行判断
            if (userService.findUserByName(userName) == null && !(userName.equals(""))) {
                // 提示信息隐藏
                lblUserNameError.setForeground(SystemColor.control);
                // 对勾显示
                lblUserNameRight.setForeground(SystemColor.GREEN);
            } else if (!userName.equals("")) {
                // 提示信息改成red
                lblUserNameError.setForeground(SystemColor.RED);
                // 对勾改成control颜色
                lblUserNameRight.setForeground(SystemColor.control);
            } else {
                // 提示信息改成red
                lblUserNameError.setForeground(SystemColor.RED);
                lblUserNameError.setText("用户名不能为空");
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            // 获取用户名
            String userName = txtUserName.getText().trim();
            lblUserNameError.setText("该用户名已被注册");
            // 进行判断
            if (userService.findUserByName(userName) == null && !(userName.equals(""))) {
                // 提示信息隐藏
                lblUserNameError.setForeground(SystemColor.control);
                // 对勾显示
                lblUserNameRight.setForeground(SystemColor.GREEN);
            } else if(!userName.equals("")){
                // 提示信息改成red
                lblUserNameError.setForeground(SystemColor.RED);
                // 对勾改成control颜色
                lblUserNameRight.setForeground(SystemColor.control);
            } else {
                // 提示信息改成red
                lblUserNameError.setForeground(SystemColor.RED);
                lblUserNameError.setText("用户名不能为空");
            }
        }
    }

    /**
     * 监听类，负责处理用户密码文本框
     */
    public class PasswordListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            // 获取用户输入的密码
            String password = new String(txtUserPwd.getPassword());
            // 获取确认密码
            String rePassword = new String(txtUserRePwd.getPassword());
            // 进行判断
            if ("".equals(password)) {
                lblUserPwdError.setForeground(SystemColor.control);
                lblUserPwdRight.setForeground(SystemColor.control);
                // 密码不合法不显示
                lblUserRePwdError.setForeground(SystemColor.control);
                lblUserRePwdRight.setForeground(SystemColor.control);
            } else if (password.length() >= 6) {
                lblUserPwdError.setForeground(SystemColor.control);
                lblUserPwdRight.setForeground(SystemColor.GREEN);
                // 密码合法进行进一步判断
                if (!password.equals(rePassword) && !("".equals(rePassword))) {
                    lblUserRePwdError.setForeground(SystemColor.RED);
                    lblUserRePwdRight.setForeground(SystemColor.control);
                } else if (password.equals(rePassword) && !("".equals(rePassword))) {
                    lblUserRePwdError.setForeground(SystemColor.control);
                    lblUserRePwdRight.setForeground(SystemColor.GREEN);
                }
            } else {
                lblUserPwdError.setForeground(SystemColor.RED);
                lblUserPwdRight.setForeground(SystemColor.control);
                // 密码不合法不显示
                lblUserRePwdError.setForeground(SystemColor.control);
                lblUserRePwdRight.setForeground(SystemColor.control);
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            // 获取用户输入的密码
            String password = new String(txtUserPwd.getPassword());
            // 获取确认密码
            String rePassword = new String(txtUserRePwd.getPassword());

            // (空或密码不合法不显示)
            if ("".equals(password)) {
                lblUserPwdError.setForeground(SystemColor.control);
                lblUserPwdRight.setForeground(SystemColor.control);
                // 密码不合法不显示
                lblUserRePwdError.setForeground(SystemColor.control);
                lblUserRePwdRight.setForeground(SystemColor.control);
            } else if (password.length() < 6) {
                lblUserPwdError.setForeground(SystemColor.RED);
                lblUserPwdRight.setForeground(SystemColor.control);
                // 密码不合法不显示
                lblUserRePwdError.setForeground(SystemColor.control);
                lblUserRePwdRight.setForeground(SystemColor.control);
            } else {
                lblUserPwdError.setForeground(SystemColor.control);
                lblUserPwdRight.setForeground(SystemColor.GREEN);
                // 密码合法进行进一步判断
                if (!password.equals(rePassword) && !("".equals(rePassword))) {
                    lblUserRePwdError.setForeground(SystemColor.RED);
                    lblUserRePwdRight.setForeground(SystemColor.control);
                } else if (password.equals(rePassword) && !("".equals(rePassword))) {
                    lblUserRePwdError.setForeground(SystemColor.control);
                    lblUserRePwdRight.setForeground(SystemColor.GREEN);
                }
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }

    /**
     * 监听类，负责处理用户确认密码文本框
     */
    public class RePasswordListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            // 获取密码和确认密码
            String password = new String(txtUserPwd.getPassword());
            String rePassword = new String(txtUserRePwd.getPassword());

            // 进行判断 (空或密码不合法不显示)
            if ("".equals(rePassword) || password.length() < 6) {
                lblUserRePwdError.setForeground(SystemColor.control);
                lblUserRePwdRight.setForeground(SystemColor.control);
            } else if (password.equals(rePassword)) {
                lblUserRePwdError.setForeground(SystemColor.control);
                lblUserRePwdRight.setForeground(SystemColor.GREEN);
            } else {
                lblUserRePwdError.setForeground(SystemColor.RED);
                lblUserRePwdRight.setForeground(SystemColor.control);
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            // 获取密码和确认密码
            String password = new String(txtUserPwd.getPassword());
            String rePassword = new String(txtUserRePwd.getPassword());

            // 进行判断 (空或密码不合法不显示)
            if ("".equals(rePassword) || password.length() < 6) {
                lblUserRePwdError.setForeground(SystemColor.control);
                lblUserRePwdRight.setForeground(SystemColor.control);
            } else if (password.equals(rePassword)) {
                lblUserRePwdError.setForeground(SystemColor.control);
                lblUserRePwdRight.setForeground(SystemColor.GREEN);
            } else {
                lblUserRePwdError.setForeground(SystemColor.RED);
                lblUserRePwdRight.setForeground(SystemColor.control);
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }

    /**
     * 重置监听
     */
    public class ResetListener implements ActionListener {
        /**
         * 重写
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // 清空姓名、密码、确认密码
            txtUserName.setText("");
            txtUserPwd.setText("");
            txtUserRePwd.setText("");
            // 重置单选按钮为未选择
            rbMale.setSelected(false);
            rbFemale.setSelected(false);
            // 重置所有复选按钮
            ckbRead.setSelected(false);
            ckbNet.setSelected(false);
            ckbSwim.setSelected(false);
            ckbTour.setSelected(false);
            // 清空地址栏
            txtUserAdress.setText("");
            // 重置组合框为未选择状态
            cmbUserDegree.setSelectedIndex(0);
            // 重置提示语
            lblUserNameError.setForeground(SystemColor.control);
            lblUserNameRight.setForeground(SystemColor.control);
            lblUserPwdError.setForeground(SystemColor.control);
            lblUserPwdRight.setForeground(SystemColor.control);
            lblUserRePwdError.setForeground(SystemColor.control);
            lblUserRePwdRight.setForeground(SystemColor.control);
        }
    }

    /**
     * 注册监听
     */
    public class RegisterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // 获取用户输入的数据
            String userName = txtUserName.getText().trim();
            String password = new String(txtUserPwd.getPassword());
            String rePassword = new String(txtUserRePwd.getPassword());

            // 获取用户性别
            int sex = rbMale.isSelected() ? 0 : 1;

            // 获取爱好
            String hobby = (ckbRead.isSelected() ? "阅读 " : "") + (ckbNet.isSelected() ? "上网 " : "")
                    + (ckbSwim.isSelected() ? "游泳 " : "") + (ckbTour.isSelected() ? "旅游 " : "");

            // 获取地址
            String address = txtUserAdress.getText().trim();

            // 学历
            String degree = cmbUserDegree.getSelectedItem().toString().trim();

            // 判断用户名是否已经存在
            if (userService.findUserByName(userName) != null) {
                JOptionPane.showMessageDialog(null, "该用户已经存在", "警告提示", JOptionPane.WARNING_MESSAGE);
                // 重置为空
                resetButton.doClick();
            } else if ("".equals(userName)) {
                JOptionPane.showMessageDialog(null, "用户名不能为空", "警告提示", JOptionPane.WARNING_MESSAGE);
            } else if ("".equals(password)) {
                JOptionPane.showMessageDialog(null, "密码不能为空", "警告提示", JOptionPane.WARNING_MESSAGE);
            } else if (!rePassword.equals(password)) {
                JOptionPane.showMessageDialog(null, "确认密码与密码不匹配", "警告提示", JOptionPane.WARNING_MESSAGE);
            } else if (rbMale.isSelected() == false && rbFemale.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "未选择性别", "警告提示", JOptionPane.WARNING_MESSAGE);
            } else if (ckbNet.isSelected() == false && ckbRead.isSelected() == false && ckbSwim.isSelected() == false
                    && ckbTour.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "请选择你的爱好哟！", "友情提示", JOptionPane.WARNING_MESSAGE);
            } else if ("".equals(address)) {
                JOptionPane.showMessageDialog(null, "地址不能为空", "警告提示", JOptionPane.WARNING_MESSAGE);
            } else if ("".equals(degree)) {
                JOptionPane.showMessageDialog(null, "请选择你的学历", "友情提示", JOptionPane.WARNING_MESSAGE);
            } else {
                // 将数据封装到对象中
                user = new User(userName, password, sex, hobby, address, degree);
                // 保存数据
                if (userService.saveUser(user)) {
                    // 注册成功
                    JOptionPane.showMessageDialog(null, "注册成功！\n"+"你的用户名为:"+userName, "成功提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "注册失败！", "错误提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * 返回监听
     */
    public class returnLoginListener implements ActionListener {
        /**
         * 重写
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            RegistFrame.this.setVisible(false);
            new LoginFrame("登录");
        }
    }
}

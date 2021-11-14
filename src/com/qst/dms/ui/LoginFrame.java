package com.qst.dms.ui;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import java.awt.event.*;

import com.qst.dms.entity.User;
import com.qst.dms.service.UserService;

/**
 * @Author: Richie
 * @Date: 2021/07/18
 * @LastEditTime: 2021/07/18
 * @LastEditors: Richie
 */

/**
 * 用户登录窗口,登录成功则进入系统主界面，登陆不成功则提示，无账号则可以点击注册按钮进入注册界面
 */
public class LoginFrame extends JFrame {

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
    private JLabel lblUserName, lblUserPwd;

    /**
     * 用户名，文本框
     */
    private JTextField txtUserName;

    /**
     * 密码，密码框
     */
    private JPasswordField txtUserPwd;

    /**
     * 确认、取消和注册，按钮
     */
    private JButton btnOk, btnCancle, btnRegist;

    /**
     * 登录用户
     */
    private static User user;

    /**
     * 用户业务类
     */
    private UserService userService;

    /**
     * 构造方法
     */
    public LoginFrame(String title) {
        super(title);
        setTitle("用户登录");

        // 实例化用户业务类对象
        userService = new UserService();

        // 设置窗体的icon
        ImageIcon icon = new ImageIcon("images\\dms.png");
        this.setIconImage(icon.getImage());

        // 实例化组件
        contentPane = new JPanel();

        // 使用null布局
        contentPane.setLayout(null);

        // 设置全局字体
        InitGlobalFont(new Font("楷体", Font.PLAIN, 21));

        lblUserName = new JLabel("用户名：");
        lblUserPwd = new JLabel("密  码：");
        txtUserName = new JTextField(30);
        txtUserPwd = new JPasswordField(30);
        txtUserPwd.setEchoChar('*');
        txtUserPwd.addActionListener(new JPasswordFieldListener());

        btnOk = new JButton("登录");
        btnOk.addActionListener(new LoginListener());

        btnCancle = new JButton("重置");
        btnCancle.addActionListener((ActionListener) new ResetListener());

        btnRegist = new JButton("注册");
        btnRegist.addActionListener(new RegistListener());

        lblUserName.setBounds(65, 50, 90, 30);
        lblUserPwd.setBounds(65, 100, 90, 30);
        txtUserName.setBounds(145, 55, 160, 30);
        txtUserPwd.setBounds(145, 105, 160, 30);
        btnOk.setBounds(40, 160, 80, 40);
        btnCancle.setBounds(150, 160, 80, 40);
        btnRegist.setBounds(260, 160, 80, 40);

        contentPane.add(lblUserName);
        contentPane.add(txtUserName);
        contentPane.add(lblUserPwd);
        contentPane.add(txtUserPwd);
        contentPane.add(btnOk);
        contentPane.add(btnCancle);
        contentPane.add(btnRegist);
        
        //是否关闭
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int flag = JOptionPane.showConfirmDialog(null, "是否确认退出系统?",
                        "提示!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if(JOptionPane.YES_OPTION == flag){
                    System.exit(0);
                }else{
                    return;
                }
            }
        });

        // 主面板放入窗体中
        this.add(contentPane);
        // 设置窗体大小和位置
        this.setSize(400, 300);
        // 设置窗口在屏幕中央
        this.setLocationRelativeTo(null);
        // 设置窗体不能改变大小
        this.setResizable(false);
        // 设置窗体初始可见
        this.setVisible(true);
    }

    /**
     * 监听类，负责处理登录按钮
     */
    public class LoginListener implements ActionListener {

        // 重写actionPerFormed()方法，事件处理逻辑
        public void actionPerformed(ActionEvent e) {

            // 根据用户名查询用户
            user = userService.findUserByName(txtUserName.getText().trim());

            // 判断用户是否存在
            if (user != null) {
                // 判断输入的密码是否正确
                if (user.getPassword().equals(new String(txtUserPwd.getPassword()))) {

                    // 登录成功，隐藏登录窗口
                    LoginFrame.this.setVisible(false);

                    // 显示主窗口
                    new MainFrame();
                } else {

                    // 输出提示信息
                    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("楷体", Font.BOLD, 18)));
                    JOptionPane.showMessageDialog(null, "密码错误！请重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE);

                    // 清空密码框
                    txtUserPwd.setText("");
                }
            } else {
                // 输出提示信息
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("楷体", Font.BOLD, 18)));
                JOptionPane.showMessageDialog(null, "该用户不存在，请先注册！", "错误提示", JOptionPane.ERROR_MESSAGE);
                txtUserName.setText("");
                txtUserPwd.setText("");
            }
        }
    }

    /**
     * 监听类，负责处理重置按钮
     */
    public class ResetListener implements ActionListener {

        // 重写actionPerFormed()方法，事件处理方法
        @Override
        public void actionPerformed(ActionEvent e) {
            // 清空文本框
            txtUserName.setText("");
            txtUserPwd.setText("");
        }
    }

    /**
     * 监听类，负责处理密码框直接登录
     */
    public class JPasswordFieldListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            btnOk.doClick();
        }
    }

    /**
     * 监听类，负责处理注册按钮
     */
    public class RegistListener implements ActionListener {

        // 重写actionPerFormed()方法，事件处理方法
        public void actionPerformed(ActionEvent e) {
            // 创建注册窗
            LoginFrame.this.setVisible(false);
            new RegistFrame("用户注册");
        }
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

    public static void main(String[] args) {
        
        new LoginFrame("登录");
    }
}
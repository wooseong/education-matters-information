import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

public class SwingTree extends JFrame {
  JTextField textField = new JTextField();

  JScrollPane scrollPane = new JScrollPane();

  JTree tree;

  DefaultMutableTreeNode root = new DefaultMutableTreeNode("메뉴");

  DefaultMutableTreeNode menu1 = new DefaultMutableTreeNode("학적/졸업");
  DefaultMutableTreeNode menu1_1 = new DefaultMutableTreeNode("내정보관리 및 증명서신청");
  DefaultMutableTreeNode menu1_2 = new DefaultMutableTreeNode("학정변동 및 (타)전공신청");
  DefaultMutableTreeNode menu1_3 = new DefaultMutableTreeNode("졸업적부심사 및 자가진단");

  DefaultMutableTreeNode menu2 = new DefaultMutableTreeNode("수업/성적");
  DefaultMutableTreeNode menu2_1 = new DefaultMutableTreeNode("강좌조회 및 수강신청");
  DefaultMutableTreeNode menu2_2 = new DefaultMutableTreeNode("공결신청 및 채플출결조히");
  DefaultMutableTreeNode menu2_3 = new DefaultMutableTreeNode("성적");
  
  DefaultMutableTreeNode menu3 = new DefaultMutableTreeNode("장학/등록 및 학생지원");
  DefaultMutableTreeNode menu3_1 = new DefaultMutableTreeNode("장학이력 및 고지서출력");
  
  DefaultMutableTreeNode menu4 = new DefaultMutableTreeNode("포상/징계");
  DefaultMutableTreeNode menu4_1 = new DefaultMutableTreeNode("포상/징계");


  public SwingTree() {
    root.add(menu1);
    root.add(menu2);
    root.add(menu3);
    root.add(menu4);
    
    menu1.add(menu1_1);
    menu1.add(menu1_2);
    menu1.add(menu1_3);
    menu1_1.add(new DefaultMutableTreeNode("학사일정조회"));
    menu1_1.add(new DefaultMutableTreeNode("신상정보관리"));
    menu1_1.add(new DefaultMutableTreeNode("비밀번호변경"));
    menu1_1.add(new DefaultMutableTreeNode("증명서 우편발송 신청"));
    menu1_1.add(new DefaultMutableTreeNode("인터넷 증명발급 센터"));
    menu1_2.add(new DefaultMutableTreeNode("학적변동신청"));
    menu1_3.add(new DefaultMutableTreeNode("졸업인증"));
    
    menu2.add(menu2_1);
    menu2.add(menu2_2);
    menu2.add(menu2_3);
    menu2_1.add(new DefaultMutableTreeNode("강의시간표/수업계획조회"));
    menu2_1.add(new DefaultMutableTreeNode("관심과목 담기"));
    menu2_1.add(new DefaultMutableTreeNode("수강신청"));
    menu2_1.add(new DefaultMutableTreeNode("수강내역조회/출력"));
    menu2_1.add(new DefaultMutableTreeNode("수강철회신청"));
    menu2_2.add(new DefaultMutableTreeNode("공결신청"));
    menu2_3.add(new DefaultMutableTreeNode("기이수성적조회"));
    
    menu3.add(menu3_1);
    menu3_1.add(new DefaultMutableTreeNode("장학이력조회"));
    
    menu4.add(menu4_1);
    menu4_1.add(new DefaultMutableTreeNode("포상/징계이력조회"));
    
    tree = new JTree(root);

    getContentPane().setLayout(new BorderLayout());
    tree.addTreeSelectionListener(new TreeHandler());
    scrollPane.getViewport().add(tree);
    getContentPane().add("Center", scrollPane);
    getContentPane().add("South", textField);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setSize(500, 500);
    setVisible(true);
  }

  public static void main(String[] args) {
    SwingTree app = new SwingTree();
  }

  public class TreeHandler implements TreeSelectionListener {
    public void valueChanged(TreeSelectionEvent e) {
      TreePath path = e.getPath();
      String text = path.getPathComponent(path.getPathCount() - 1).toString();
      
      if (text.equals("학적/졸업")) {
        JOptionPane.showMessageDialog(null, "학적/졸업");
      }
      else if(text.equals("수업/성적")) {
        JOptionPane.showMessageDialog(null, "수업/성적");
      }
      
      else if(text.equals("장학/등록 및 학생지원")) {
          JOptionPane.showMessageDialog(null, "장학/등록 및 학생지원");
        }
      else if(text.equals("포상/징계")) {
          JOptionPane.showMessageDialog(null, "포상/징계");
        }
      textField.setText(text);
    }
  }
}

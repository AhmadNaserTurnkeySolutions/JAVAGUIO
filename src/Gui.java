import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class Gui extends JFrame implements ActionListener{

	public JPanel p1,p2,p3;
	public JLabel l1,l2,l3,l4,l5;
	public JTextField t1,t2,t3,t4;
	public JButton b1,b2,b3,b4,b5;
	public JComboBox c1;
	public ArrayList<Student> l;
	public String cs[]=new String[]{"freshman", "sophomore", "junior","senior"};
	int start;
	int last;
	int current;
	public Gui()
	{
		this.c1=new JComboBox(cs);
		
		this.l1=new JLabel("File Name");
		
		this.l2=new JLabel("Id");
		
		this.l3=new JLabel("Name");
		
		this.l4=new JLabel("avg");
		
		this.l5=new JLabel("status");
		
		
		
		this.b1=new JButton("Load");
		this.b2=new JButton("Save");
		this.b3=new JButton("Next");
		this.b4=new JButton("New");
		this.b5=new JButton("Add");
		
		this.t1=new JTextField(10);
		this.t2=new JTextField(10);
		this.t3=new JTextField(10);
		this.t4=new JTextField(10);
		
		
		this.p1=new JPanel(new FlowLayout());
		this.p2=new JPanel(new GridLayout(4,2));
		this.p3=new JPanel(new FlowLayout());
		
		this.p1.add(this.l1);
		this.p1.add(this.t1);
		this.p1.add(this.b1);
		this.p1.add(this.b2);
		
		this.p2.add(this.l2);
		this.p2.add(this.t2);
		this.p2.add(this.l3);
		this.p2.add(this.t3);
		this.p2.add(this.l4);
		this.p2.add(this.t4);
		this.p2.add(this.l5);
		this.p2.add(this.c1);
		
		this.p3.add(this.b3);
		this.p3.add(this.b4);
		this.p3.add(this.b5);
		
		this.b1.addActionListener(this);
		this.b2.addActionListener(this);
		this.b3.addActionListener(this);
		this.b4.addActionListener(this);
		this.b5.addActionListener(this);
		
		
		this.add(p1,BorderLayout.NORTH);
		this.add(p2,BorderLayout.CENTER);
		this.add(p3,BorderLayout.SOUTH);
		
		this.setTitle("Student Frame");
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.b2)
		{
		
			
	String str="";
	String t="";
	
			for(int i=0;i<l.size();i++)
			{
				str+=l.get(i).ID+"%%";
				str+=l.get(i).Name+"%%";
				str+=l.get(i).Avg+"%%";
				str+=l.get(i).Status;
				str+="\n";
				
				t+=l.get(i).ID+" | ";
				t+=l.get(i).Name+" | ";
				t+=l.get(i).Avg+" | ";
				t+=l.get(i).Status;
				t+="\n";
			
			}
			TextIO te=new TextIO();
			
			try {
				te.WiteFile(str);
				JOptionPane.showMessageDialog(null, "Write Done : \n"+t);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(e.getSource()==this.b5)
		{
			System.out.println(l.size());
			
			String id=t2.getText();
			boolean flag=true;
			for(int i=0;i<l.size();i++)
			{
				
				if(l.get(i).ID.equals(id))
				{
					flag=false;
					JOptionPane.showMessageDialog(null, "this ID already exists , cant add");
				}
			}
			if(flag==true){
			Student st=new Student(this.t3.getText(),
					this.t2.getText(),
					this.t4.getText(),
					this.c1.getSelectedIndex()+"");//x[0],x[2],x[3]);
			l.add(st);
			last=l.size();

			JOptionPane.showMessageDialog(null, "Done");
			System.out.println(l.size());
			}
		}
		if(e.getSource()==this.b4)
		{
			

				this.t2.setText("");
				this.t3.setText("");
				this.t4.setText("");
				this.c1.setSelectedIndex(0);
			}
		// TODO Auto-generated method stub
		if(e.getSource()==this.b3)
		{
			
			current++;
			if(current<last)
			{
				this.t2.setText(l.get(current).ID);
				this.t3.setText(l.get(current).Name);
				this.t4.setText(l.get(current).Avg);
				this.c1.setSelectedIndex(Integer.parseInt(l.get(current).Status));
				
			}
			else
			{
				current=0;
				this.t2.setText(l.get(current).ID);
				this.t3.setText(l.get(current).Name);
				this.t4.setText(l.get(current).Avg);
				this.c1.setSelectedIndex(Integer.parseInt(l.get(current).Status));
				
			}
		}
		if(e.getSource()==this.b1) 
		{
			TextIO t=new TextIO();
			t.readFile();
			String s[]=t.stringToArray();
		l=new ArrayList<Student>();
			for(int i=0;i<s.length;i++)
			{
				String x[]=s[i].split("%%");
				Student st=new Student(x[1],x[0],x[2],x[3]);
	//JOptionPane.showMessageDialog(null,x[i]);
			l.add(st);
			}
			start=0;
			current=0;
			last=l.size();
			
			this.t2.setText(l.get(0).ID);
			this.t3.setText(l.get(0).Name);
			this.t4.setText(l.get(0).Avg);
			this.c1.setSelectedIndex(Integer.parseInt(l.get(0).Status));
		}
		
	}

}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ui extends JFrame implements ActionListener {
    JTextField[] boxes; //up,right,down,left

    int current = 0;
    short count = 0;
    FileWriter f;

    {
        try {
            f = new FileWriter(new File("level0.lvl"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ui(){
        super("0=frei;1=Wand(fest);2=frei(zum einstürzen)");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);


        boxes = new JTextField[4];
        for(int x  = 0;x<4;++x){
            boxes[x] = new JTextField(""+x);
        }
        boxes[0].setBounds(20,30,30,30);
        boxes[1].setBounds(40,50,30,30);
        boxes[2].setBounds(20,80,30,30);
        boxes[3].setBounds(0,50,30,30);

        for(int x  = 0;x<4;++x){
            this.add(boxes[x]);
        }

        JButton confirm = new JButton("confirm");
        confirm.addActionListener(this);
        confirm.setBounds(60,60,100,30);
        this.add(confirm);

this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args){
        new ui();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int toAdd = parse(boxes[0].getText()) |parse(boxes[1].getText()) <<2 |parse(boxes[2].getText()) <<4|parse(boxes[3].getText())<<6 ;
        current = current<<8*count | toAdd;
        ++count;
        if(count >= 4){
            try {
                f.write(toAdd);
                f.flush();
                count = 0;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    int parse(String zahl){
       return Integer.parseInt(zahl) & 0b00000011;
    }
}


package clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CelesteZaldivar
 */
public class Color_fila_tabla extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {  
        super.getTableCellRendererComponent(table, value, hasFocus, hasFocus, row, column);
         
     
           String dato=value.toString();
        switch(dato)
        {
            case "Rechazada":
                this.setBackground(Color.orange);
                break;
            case "Pendiente":
               this.setBackground(Color.gray);
                break;
            case "Aprobada":
               this.setBackground(Color.green);
                break;
        }
      
        return this;
    }
   
}

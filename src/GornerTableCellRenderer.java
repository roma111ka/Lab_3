import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import static java.lang.StrictMath.abs;

public class GornerTableCellRenderer  implements TableCellRenderer  {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();

    private String needle = null;

    private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();
    private Boolean vis=true;

    public GornerTableCellRenderer ()
    {
        // Показывать только 5 знаков после запятой
        formatter.setMaximumFractionDigits(5);
        // Не использовать группировку (т.е. не отделять тысячи
        // ни запятыми, ни пробелами), т.е. показывать число как "1000",
        // а не "1 000" или "1,000"
        formatter.setGroupingUsed(false);
        // Установить в качестве разделителя дробной части точку, а не
        // запятую. По умолчанию, в региональных настройках
        // Россия/Беларусь дробная часть отделяется запятой
        DecimalFormatSymbols dottedDouble =
                formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        // Разместить надпись внутри панели
        panel.add(label);
        // Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        // Преобразовать double в строку с помощью форматировщика
        String formattedDouble = formatter.format(value);
        // Установить текст надписи равным строковому представлению числа
        label.setText(formattedDouble);
        String format_digit=formatter.format(value);
        double digit=Double.parseDouble(format_digit);
        double val=digit;
        val=abs(val);
        boolean flag1=true;
        boolean flag2=true;
        int startRes=(int)val;
        double  end=val;
        end-=startRes;
        double scale = Math.pow(10, 5);
        end=Math.round(end * scale) / scale;
        String b= String.valueOf(end);
        int length=b.length()-2;
        end*=Math.pow(10,length);
        end= Math.round(end);
        int endRes=(int)end;

        while (1==1)
        {
            if (startRes%2==0)
            {
                startRes/=10;
            }
            else {
                flag1=false;
                break;
            }
            if (startRes%10<10)
            {
                if (startRes%2==0)
                {
                    flag1 =true;
                    break;
                }
                else{ flag1=false;
                    break;}
            }
        }
        while (1==1)
        {
            if (endRes%2==0)
            {
                endRes/=10;
            }
            else {
                flag2=false;
                break;
            }
            if (endRes%10<10)
            {
                if (endRes%2==0)
                {
                    flag2 =true;
                    break;
                }
                else{ flag2=false;
                    break;}
            }
        }

        if (flag1==true&&flag2==true) {
            this.vis=true;
        } else
        {
            this.vis=false;

        }

        if (this.vis==true) {
            panel.setBackground(Color.BLACK);
            label.setForeground(Color.white);
        } else {
            panel.setBackground(Color.WHITE);
            label.setForeground(Color.BLACK);
        }

        if (col==1 && needle!=null && needle.equals(formattedDouble)) {
            // Номер столбца = 1 (т.е. второй столбец) + иголка не null
            // (значит что-то ищем) +
            // значение иголки совпадает со значением ячейки таблицы -
            // окрасить задний фон панели в красный цвет
            panel.setBackground(Color.RED);
        }
        return panel;
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }
}
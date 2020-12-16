import javax.swing.table.AbstractTableModel;

import static java.lang.StrictMath.abs;

@SuppressWarnings("serial")
public class GornerTableModel  extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    private double result;
    public GornerTableModel(Double from, Double to, Double step,
                            Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
// В данной модели 3 столбца
        return 3;
    }
    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }

    public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step*row;
        switch (col){
            case 0:
                return x;
            case 1:
            {
                result = 0.0;
                for(int i = 0; i < coefficients.length; i++){

                    result += Math.pow(x, coefficients.length-1-i)*coefficients[i];
                }
                double scale = Math.pow(10, 5);
                return Math.round(result * scale) / scale;

            }


            default:
            {


                if (result == 0)
                    return "good";
                else return "pain";
            }

        }
    }
    public String getColumnName(int col) {
        switch(col)
        {
            case(0):
                return "Значение Х";
            case(1):
                return "Значение многочлена";

            default:
                return "Life is ?";

        }
    }
    public Class<?> getColumnClass(int col) {
        // И в 1-ом и во 2-ом столбце находятся значения типа Double
        switch (col){
            case 0:
                return Double.class;
            case 1:
                return Double.class;

            default:
                return String.class;

        }
    }
}

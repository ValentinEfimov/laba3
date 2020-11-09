package bsu.rfe.java.group7.lab3.Efimov.varB5;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel{
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    public  GornerTableModel(Double from, Double to, Double step,
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
// В данной модели два столбца
        return 3;
    }
    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }

    public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step * row;


         Double  result;
        switch (col) {
            case 0: {
// Если запрашивается значение 1-го столбца, то это X
                return x;
            } case 1: {
// Если запрашивается значение 2-го столбца, то это значение
// многочлена
                result = 0.0;
                for (int i = 0; i < coefficients.length; i++) {
                    result += x * coefficients[i];

                }
                return result;
// Если запрашивается значение 3-го столбца, то это значение true если целая часть многочлена-простое число
            } default: {
                result = 0.0;
                for (int i = 0; i < coefficients.length; i++) {
                    result += x * coefficients[i];
                }
                int num = result.intValue(), temp;
                if(num==0) return false;
                else{
                Boolean isPrime = true;
                // проверяем число на простоту
                for (int i = 2; i <= num / 2; i++) {
                    temp = num % i;
                    if (temp == 0 ) {
                        isPrime = false;
                        break;
                    }
                }
                return isPrime;
                }
            }
        }
    }
    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
            case 1:
// Название 1-го столбца
                return "Значение многочлена";
            default:
// Название 2-го столбца
                return "Значение простое?";
        }
    }
    public Class<?> getColumnClass(int col) {
// И в 1-ом и во 2-ом столбце находятся значения типа Double в 3-ем Bool
        switch (col) {
            case 2:return Boolean.class;
            default: return  Double.class;
        }

    }

}

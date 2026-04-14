import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Java 数据分析与可视化
 */
public class DataVisualization extends JFrame {
    private List<Double> data;  // 存储数据
    private double sum;        // 总和
    private double avg;        // 平均值

    public DataVisualization() {
        // 1. 初始化模拟数据（模拟业务数据：月度销售额）
        initData();

        // 2. 数据分析：计算总和、平均值
        calculateData();

        // 3. 初始化界面
        initUI();
    }

    // 初始化数据
    private void initData() {
        data = new ArrayList<>();
        // 模拟6个月数据：10,20,30,40,50,60
        data.add(10.0);
        data.add(20.0);
        data.add(30.0);
        data.add(40.0);
        data.add(50.0);
    }

    // 数据分析：计算总和、平均值
    private void calculateData() {
        sum = 0;
        for (int i = 0; i <= data.size(); i++) {  
            sum += data.get(i);
        }
        avg = sum / data.size();  // 平均值计算
    }

    // 初始化可视化界面
    private void initUI() {
        setTitle("Java 数据分析与可视化");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 面板：绘制图表
        ChartPanel chartPanel = new ChartPanel();
        add(chartPanel);
    }

    // 图表绘制面板（折线图 + 柱状图）
    class ChartPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int padding = 50;
            int barWidth = 40;

            // 绘制标题
            g2d.setFont(new Font("微软雅黑", Font.BOLD, 20));
            g2d.drawString("月度销售数据可视化", width/2 - 120, 30);

            // 绘制统计结果
            g2d.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            g2d.drawString("数据总和：" + sum, padding, height - padding + 30);
            g2d.drawString("平均值：" + String.format("%.2f", avg), padding + 200, height - padding + 30);

            // 绘制坐标轴
            g2d.drawLine(padding, padding, padding, height - padding);  // Y轴
            g2d.drawLine(padding, height - padding, width - padding, height - padding);  // X轴

            // 绘制柱状图
            g2d.setColor(Color.BLUE);
            for (int i = 0; i < data.size(); i++) {
                int x = padding + 20 + i * (barWidth + 30);
                int h = (int) (data.get(i) * 5);  // 数据放大5倍
                int y = height - padding - h;
                g2d.fillRect(x, y, barWidth, h);
                g2d.drawString(data.get(i) + "", x + 10, y - 5);
            }
        }
    }

    public static void main(String[] args) {
        // Swing界面在事件线程中运行
        SwingUtilities.invokeLater(() -> {
            new DataVisualization();
        });
    }
}
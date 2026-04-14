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
        data.add(60.0);  // 修复：添加第6个月数据
    }

    // 数据分析：计算总和、平均值
    private void calculateData() {
        sum = 0;
        for (int i = 0; i < data.size(); i++) {  // 修复：i <= 改为 i <
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
        
        setVisible(true);  // 修复：添加窗口可见性
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
            int bottomPadding = 80;  // 底部留出更多空间
            int barWidth = 40;

            // 绘制标题（距离顶部边界约8像素，即2mm）
            g2d.setFont(new Font("微软雅黑", Font.BOLD, 20));
            g2d.drawString("月度销售数据可视化", width/2 - 120, 28);

            // 绘制坐标轴
            g2d.drawLine(padding, padding, padding, height - bottomPadding);  // Y轴
            g2d.drawLine(padding, height - bottomPadding, width - padding, height - bottomPadding);  // X轴

            // 绘制X轴标签（月份）
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("微软雅黑", Font.PLAIN, 14));
            String[] months = {"1月", "2月", "3月", "4月", "5月", "6月"};
            for (int i = 0; i < data.size(); i++) {
                int x = padding + 20 + i * (barWidth + 30) + barWidth / 2 - 10;
                g2d.drawString(months[i], x, height - bottomPadding + 20);
            }

            // 绘制统计结果（距离底部边界约8像素，即2mm）
            g2d.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            g2d.drawString("数据总和：" + sum, padding, height - 12);
            g2d.drawString("平均值：" + String.format("%.2f", avg), padding + 200, height - 12);

            // 绘制柱状图
            g2d.setColor(Color.BLUE);
            for (int i = 0; i < data.size(); i++) {
                int x = padding + 20 + i * (barWidth + 30);
                int h = (int) (data.get(i) * 5);  // 数据放大5倍
                int y = height - bottomPadding - h;
                g2d.fillRect(x, y, barWidth, h);
                g2d.drawString(data.get(i) + "", x + 10, y - 5);
            }

            // 绘制折线图
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(2.0f));
            for (int i = 0; i < data.size() - 1; i++) {
                int x1 = padding + 20 + i * (barWidth + 30) + barWidth / 2;
                int y1 = height - bottomPadding - (int) (data.get(i) * 5);
                int x2 = padding + 20 + (i + 1) * (barWidth + 30) + barWidth / 2;
                int y2 = height - bottomPadding - (int) (data.get(i + 1) * 5);
                g2d.drawLine(x1, y1, x2, y2);
            }
            
            // 绘制折线图上的数据点
            g2d.setColor(Color.RED);
            for (int i = 0; i < data.size(); i++) {
                int x = padding + 20 + i * (barWidth + 30) + barWidth / 2;
                int y = height - bottomPadding - (int) (data.get(i) * 5);
                g2d.fillOval(x - 5, y - 5, 10, 10);
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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardFrame extends JFrame {
    // Theme Colors
    public static final Color PRIMARY_PURPLE = new Color(68, 22, 108);
    public static final Color SIDEBAR_DARK   = new Color(34, 13, 62);
    public static final Color ACCENT_PURPLE  = new Color(118, 48, 175);
    public static final Color BG_LAVENDER    = new Color(246, 244, 252);
    public static final Color CARD_WHITE     = new Color(255, 255, 255);
    public static final Color TEXT_DARK      = new Color(28, 22, 44);
    public static final Color TEXT_MUTED     = new Color(122, 114, 138);

    private CardLayout cardLayout;
    private JPanel contentArea;
    private JLabel pageTitleLabel;
    private JButton activeNavButton;

    public DashboardFrame() {
        setTitle("Smart Student Academic & Attendance Management System - Dashboard");
        setSize(1200, 740);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        JPanel rootPanel = new JPanel(new BorderLayout());

        // 1. Left Dark Purple Sidebar
        rootPanel.add(createSidebar(), BorderLayout.WEST);

        // 2. Right Side (Top Header + Center Switching Screens)
        JPanel rightContainer = new JPanel(new BorderLayout());
        rightContainer.setBackground(BG_LAVENDER);
        rightContainer.add(createTopBar(), BorderLayout.NORTH);

        cardLayout = new CardLayout();
        contentArea = new JPanel(cardLayout);
        contentArea.setBackground(BG_LAVENDER);

        // Add Module Screens (Dashboard is live; we will fill the others in Steps 3, 5, 6, 7)
        contentArea.add(createDashboardOverviewPanel(), "DASHBOARD");
        contentArea.add(createPlaceholderPanel("Student Management (Coming in Step 3)"), "STUDENTS");
        contentArea.add(createPlaceholderPanel("Attendance Management (Coming in Step 5)"), "ATTENDANCE");
        contentArea.add(createPlaceholderPanel("Academic Marks Management (Coming in Step 6)"), "MARKS");
        contentArea.add(createPlaceholderPanel("Reports & Exports (Coming in Step 7)"), "REPORTS");

        rightContainer.add(contentArea, BorderLayout.CENTER);
        rootPanel.add(rightContainer, BorderLayout.CENTER);

        setContentPane(rootPanel);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(245, 0));
        sidebar.setBackground(SIDEBAR_DARK);

        // Top Brand Logo
        JPanel brandPanel = new JPanel(new GridLayout(2, 1));
        brandPanel.setBackground(SIDEBAR_DARK);
        brandPanel.setBorder(new EmptyBorder(25, 24, 25, 20));

        JLabel logo = new JLabel("SSMS PORTAL");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        logo.setForeground(Color.WHITE);

        JLabel sub = new JLabel("Academic SaaS Suite");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        sub.setForeground(new Color(185, 165, 215));

        brandPanel.add(logo);
        brandPanel.add(sub);
        sidebar.add(brandPanel, BorderLayout.NORTH);

        // Navigation Menu Buttons
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(SIDEBAR_DARK);
        navPanel.setBorder(new EmptyBorder(10, 14, 10, 14));

        JButton btnDash = createNavButton("Dashboard Overview", "DASHBOARD", "Dashboard Overview");
        JButton btnStud = createNavButton("Student Management", "STUDENTS", "Student Directory & Records");
        JButton btnAtt  = createNavButton("Attendance Tracking", "ATTENDANCE", "Daily Attendance Management");
        JButton btnMark = createNavButton("Academic Marks", "MARKS", "Subject Marks & Grading");
        JButton btnRep  = createNavButton("Reports & Export", "REPORTS", "Analytics & PDF/CSV Reports");

        // Highlight Dashboard by default
        setActiveButton(btnDash);

        navPanel.add(btnDash);
        navPanel.add(Box.createVerticalStrut(8));
        navPanel.add(btnStud);
        navPanel.add(Box.createVerticalStrut(8));
        navPanel.add(btnAtt);
        navPanel.add(Box.createVerticalStrut(8));
        navPanel.add(btnMark);
        navPanel.add(Box.createVerticalStrut(8));
        navPanel.add(btnRep);

        sidebar.add(navPanel, BorderLayout.CENTER);

        // Bottom Logout Button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(SIDEBAR_DARK);
        bottomPanel.setBorder(new EmptyBorder(15, 18, 25, 18));

        JButton logoutBtn = new JButton("Sign Out");
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        logoutBtn.setForeground(new Color(255, 190, 200));
        logoutBtn.setBackground(new Color(90, 25, 55));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setOpaque(true);
        logoutBtn.setPreferredSize(new Dimension(0, 40));
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to log out?", "Confirm Logout",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                dispose();
                Main.main(null); // Re-open Login window
            }
        });

        bottomPanel.add(logoutBtn, BorderLayout.CENTER);
        sidebar.add(bottomPanel, BorderLayout.SOUTH);

        return sidebar;
    }

    private JButton createNavButton(String text, String cardKey, String headerTitle) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(new Color(215, 205, 235));
        btn.setBackground(SIDEBAR_DARK);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(12, 18, 12, 18));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (btn != activeNavButton) {
                    btn.setBackground(new Color(52, 22, 90));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (btn != activeNavButton) {
                    btn.setBackground(SIDEBAR_DARK);
                }
            }
        });

        btn.addActionListener(e -> {
            setActiveButton(btn);
            pageTitleLabel.setText(headerTitle);
            cardLayout.show(contentArea, cardKey);
        });

        return btn;
    }

    private void setActiveButton(JButton btn) {
        if (activeNavButton != null) {
            activeNavButton.setBackground(SIDEBAR_DARK);
            activeNavButton.setForeground(new Color(215, 205, 235));
        }
        activeNavButton = btn;
        activeNavButton.setBackground(PRIMARY_PURPLE);
        activeNavButton.setForeground(Color.WHITE);
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(CARD_WHITE);
        topBar.setPreferredSize(new Dimension(0, 68));
        topBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(228, 222, 240)),
                new EmptyBorder(0, 28, 0, 28)
        ));

        pageTitleLabel = new JLabel("Dashboard Overview");
        pageTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        pageTitleLabel.setForeground(TEXT_DARK);
        topBar.add(pageTitleLabel, BorderLayout.WEST);

        JLabel adminBadge = new JLabel("Admin  |  AY 2026-27");
        adminBadge.setFont(new Font("Segoe UI", Font.BOLD, 13));
        adminBadge.setForeground(PRIMARY_PURPLE);
        topBar.add(adminBadge, BorderLayout.EAST);

        return topBar;
    }

    // MODULE 2: DASHBOARD OVERVIEW PANEL
    private JPanel createDashboardOverviewPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(BG_LAVENDER);
        panel.setBorder(new EmptyBorder(24, 28, 24, 28));

        // 1. Top Row: 4 Metric Cards
        JPanel cardsGrid = new JPanel(new GridLayout(1, 4, 18, 0));
        cardsGrid.setOpaque(false);
        cardsGrid.add(createStatCard("Total Students", "128", "Across 4 Departments", PRIMARY_PURPLE));
        cardsGrid.add(createStatCard("Avg Attendance", "86.4%", "14 students > 95%", new Color(34, 155, 90)));
        cardsGrid.add(createStatCard("Average Marks", "78.2%", "II B.Tech - Sem I", ACCENT_PURPLE));
        cardsGrid.add(createStatCard("Top Performer", "94.8%", "Ananya Sharma (ECE)", new Color(225, 130, 20)));

        panel.add(cardsGrid, BorderLayout.NORTH);

        // 2. Center Row: Custom-Drawn Charts (Attendance + Department Performance)
        JPanel chartsGrid = new JPanel(new GridLayout(1, 2, 18, 0));
        chartsGrid.setOpaque(false);
        chartsGrid.add(createAttendanceChartCard());
        chartsGrid.add(createPerformanceChartCard());

        panel.add(chartsGrid, BorderLayout.CENTER);

        // 3. Bottom Row: Top Performers & Recent Activity
        JPanel bottomCard = createRoundedCard();
        bottomCard.setLayout(new BorderLayout(10, 10));
        bottomCard.setPreferredSize(new Dimension(0, 150));

        JLabel bottomTitle = new JLabel("Top Performing Students & Recent System Activity");
        bottomTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        bottomTitle.setForeground(TEXT_DARK);
        bottomCard.add(bottomTitle, BorderLayout.NORTH);

        String[] cols = {"Roll Number", "Student Name", "Department", "Attendance %", "Avg Marks %", "Status"};
        Object[][] data = {
                {"25BQ1A0401", "Ananya Sharma", "ECE", "96.0%", "94.8%", "Distinction (A+)"},
                {"25BQ1A0512", "Karthik Reddy", "CSE", "92.5%", "91.2%", "Distinction (A+)"},
                {"25BQ1A0428", "Rahul Verma", "ECE", "88.0%", "85.4%", "First Class (A)"},
                {"25BQ1A0209", "Sneha Nair", "EEE", "71.2%", "74.0%", "Low Attendance (<75%)"}
        };

        JTable table = new JTable(data, cols);
        table.setRowHeight(26);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(238, 233, 248));
        table.setEnabled(false);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(230, 225, 240)));
        bottomCard.add(scroll, BorderLayout.CENTER);

        panel.add(bottomCard, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createStatCard(String title, String value, String subtitle, Color accentColor) {
        JPanel card = createRoundedCard();
        card.setLayout(new BorderLayout(6, 6));
        card.setPreferredSize(new Dimension(0, 115));

        JLabel lblTitle = new JLabel(title.toUpperCase());
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTitle.setForeground(TEXT_MUTED);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblValue.setForeground(accentColor);

        JLabel lblSub = new JLabel(subtitle);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(TEXT_DARK);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);
        card.add(lblSub, BorderLayout.SOUTH);
        return card;
    }

    // Custom Java 2D Bar Chart for Department Attendance
    private JPanel createAttendanceChartCard() {
        JPanel card = createRoundedCard();
        card.setLayout(new BorderLayout());

        JLabel title = new JLabel("Department-Wise Attendance Overview (%)");
        title.setFont(new Font("Segoe UI", Font.BOLD, 15));
        title.setForeground(TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        JPanel chartCanvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                String[] depts = {"ECE", "CSE", "EEE", "MECH"};
                int[] values = {89, 91, 82, 78};
                Color[] barColors = {
                        PRIMARY_PURPLE,
                        ACCENT_PURPLE,
                        new Color(145, 85, 200),
                        new Color(175, 125, 220)
                };

                int w = getWidth();
                int h = getHeight();
                int bottomY = h - 35;
                int maxBarHeight = h - 75;
                int barWidth = 52;
                int spacing = (w - (depts.length * barWidth)) / (depts.length + 1);

                // Baseline
                g2.setColor(new Color(220, 215, 235));
                g2.drawLine(20, bottomY, w - 20, bottomY);

                for (int i = 0; i < depts.length; i++) {
                    int barH = (int) ((values[i] / 100.0) * maxBarHeight);
                    int x = spacing + i * (barWidth + spacing);
                    int y = bottomY - barH;

                    // Draw Rounded Bar
                    g2.setColor(barColors[i]);
                    g2.fillRoundRect(x, y, barWidth, barH, 10, 10);

                    // Percentage label above bar
                    g2.setColor(TEXT_DARK);
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                    String valStr = values[i] + "%";
                    int strW = g2.getFontMetrics().stringWidth(valStr);
                    g2.drawString(valStr, x + (barWidth - strW) / 2, y - 6);

                    // Department label below bar
                    g2.setColor(TEXT_MUTED);
                    int deptW = g2.getFontMetrics().stringWidth(depts[i]);
                    g2.drawString(depts[i], x + (barWidth - deptW) / 2, bottomY + 20);
                }
                g2.dispose();
            }
        };
        chartCanvas.setOpaque(false);
        card.add(chartCanvas, BorderLayout.CENTER);
        return card;
    }

    // Custom Java 2D Horizontal Progress Chart for Academic Performance
    private JPanel createPerformanceChartCard() {
        JPanel card = createRoundedCard();
        card.setLayout(new BorderLayout());

        JLabel title = new JLabel("Academic Grade Distribution");
        title.setFont(new Font("Segoe UI", Font.BOLD, 15));
        title.setForeground(TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        JPanel chartCanvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                String[] grades = {"Grade A+ (90-100%)", "Grade A  (80-89%)", "Grade B  (70-79%)", "Grade C  (<70%)"};
                int[] counts = {34, 48, 32, 14}; // Out of 128 students
                Color[] colors = {
                        new Color(34, 166, 92),
                        PRIMARY_PURPLE,
                        ACCENT_PURPLE,
                        new Color(235, 145, 20)
                };

                int w = getWidth() - 40;
                int startY = 30;
                int rowHeight = 46;

                for (int i = 0; i < grades.length; i++) {
                    int y = startY + i * rowHeight;

                    g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                    g2.setColor(TEXT_DARK);
                    g2.drawString(grades[i], 15, y);

                    String countLabel = counts[i] + " Students";
                    g2.setColor(TEXT_MUTED);
                    g2.drawString(countLabel, w - 55, y);

                    // Track background
                    g2.setColor(new Color(238, 234, 246));
                    g2.fillRoundRect(15, y + 8, w, 14, 8, 8);

                    // Filled progress bar
                    int fillW = (int) ((counts[i] / 60.0) * w);
                    g2.setColor(colors[i]);
                    g2.fillRoundRect(15, y + 8, fillW, 14, 8, 8);
                }
                g2.dispose();
            }
        };
        chartCanvas.setOpaque(false);
        card.add(chartCanvas, BorderLayout.CENTER);
        return card;
    }

    private JPanel createRoundedCard() {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(CARD_WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.setColor(new Color(226, 220, 238));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(16, 20, 16, 20));
        return card;
    }

    private JPanel createPlaceholderPanel(String text) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(BG_LAVENDER);
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbl.setForeground(PRIMARY_PURPLE);
        p.add(lbl);
        return p;
    }
}
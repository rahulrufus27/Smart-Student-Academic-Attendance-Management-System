import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.awt.geom.RoundRectangle2D;

public class Main {

    // ================== PREMIUM SAAS COLOR PALETTE ==================
    public static final Color BG_SIDEBAR      = new Color(22, 11, 42);    // Deepest Midnight Purple
    public static final Color BG_GRADIENT_TOP = new Color(72, 24, 118);   // Rich Royal Purple
    public static final Color PRIMARY_PURPLE  = new Color(98, 38, 158);   // Primary Brand Purple
    public static final Color ACCENT_INDIGO   = new Color(132, 68, 204);  // Interactive Hover Purple
    public static final Color BG_CANVAS       = new Color(244, 242, 250); // Soft Lavender-Gray App BG
    public static final Color CARD_WHITE      = new Color(255, 255, 255); // Pure White Surface
    public static final Color BORDER_LIGHT    = new Color(226, 220, 238); // Hairline Card Border
    public static final Color TEXT_DARK       = new Color(26, 18, 42);    // High-contrast Dark Text
    public static final Color TEXT_MUTED      = new Color(118, 110, 138); // Secondary Subtitle Text

    // Semantic Status Colors
    public static final Color EMERALD_GREEN   = new Color(16, 163, 108);
    public static final Color AMBER_GOLD      = new Color(230, 140, 20);
    public static final Color CRIMSON_RED     = new Color(220, 45, 75);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginWindow().setVisible(true);
        });
    }

    // =========================================================================
    // 1. MODERN LOGIN WINDOW
    // =========================================================================
    static class LoginWindow extends JFrame {
        private JTextField userField;
        private JPasswordField passField;
        private JLabel errorLabel;

        public LoginWindow() {
            setTitle("SSMS Portal — Admin Authentication");
            setSize(920, 540);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);

            JPanel root = new JPanel(new GridLayout(1, 2));

            // LEFT PANEL: Deep Purple Gradient with Geometric Glass Rings
            JPanel leftHero = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    GradientPaint gp = new GradientPaint(0, 0, BG_GRADIENT_TOP, getWidth(), getHeight(), BG_SIDEBAR);
                    g2.setPaint(gp);
                    g2.fillRect(0, 0, getWidth(), getHeight());

                    // Decorative translucent glowing circles
                    g2.setColor(new Color(255, 255, 255, 14));
                    g2.fillOval(-70, -70, 240, 240);
                    g2.fillOval(getWidth() - 160, getHeight() - 160, 280, 280);
                    g2.setColor(new Color(165, 105, 245, 22));
                    g2.fillOval(40, getHeight() - 120, 140, 140);

                    g2.dispose();
                }
            };
            leftHero.setLayout(new GridBagLayout());
            GridBagConstraints gbcL = new GridBagConstraints();
            gbcL.gridx = 0;
            gbcL.gridy = 0;
            gbcL.insets = new Insets(6, 35, 6, 35);

            JLabel pillBadge = new JLabel("  EDITION 2026-27  ") {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(255, 255, 255, 30));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    g2.dispose();
                    super.paintComponent(g);
                }
            };
            pillBadge.setFont(new Font("Segoe UI", Font.BOLD, 11));
            pillBadge.setForeground(new Color(230, 215, 255));
            pillBadge.setBorder(new EmptyBorder(5, 12, 5, 12));
            leftHero.add(pillBadge, gbcL);

            gbcL.gridy++;
            gbcL.insets = new Insets(16, 35, 4, 35);
            JLabel heroTitle = new JLabel("SSMS PORTAL");
            heroTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
            heroTitle.setForeground(Color.WHITE);
            leftHero.add(heroTitle, gbcL);

            gbcL.gridy++;
            gbcL.insets = new Insets(4, 35, 6, 35);
            JLabel heroSub = new JLabel("<html><center>Smart Student Academic &<br>Attendance Management System</center></html>");
            heroSub.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            heroSub.setForeground(new Color(205, 190, 235));
            leftHero.add(heroSub, gbcL);

            // RIGHT PANEL: Clean Card Form
            JPanel rightForm = new JPanel(new GridBagLayout());
            rightForm.setBackground(BG_CANVAS);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(4, 50, 4, 50);

            JLabel welcome = new JLabel("Welcome back, Admin");
            welcome.setFont(new Font("Segoe UI", Font.BOLD, 26));
            welcome.setForeground(TEXT_DARK);
            rightForm.add(welcome, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(0, 50, 18, 50);
            JLabel subText = new JLabel("Sign in to access your academic workspace");
            subText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            subText.setForeground(TEXT_MUTED);
            rightForm.add(subText, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(8, 50, 4, 50);
            JLabel uLbl = new JLabel("USERNAME");
            uLbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
            uLbl.setForeground(TEXT_MUTED);
            rightForm.add(uLbl, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(0, 50, 12, 50);
            userField = createStyledTextField("admin");
            rightForm.add(userField, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(6, 50, 4, 50);
            JLabel pLbl = new JLabel("PASSWORD");
            pLbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
            pLbl.setForeground(TEXT_MUTED);
            rightForm.add(pLbl, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(0, 50, 6, 50);
            passField = createStyledPasswordField("admin123");
            rightForm.add(passField, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(2, 50, 8, 50);
            errorLabel = new JLabel(" ");
            errorLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            errorLabel.setForeground(CRIMSON_RED);
            rightForm.add(errorLabel, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(8, 50, 10, 50);
            JButton signInBtn = createPrimaryButton("Sign In to Dashboard  →");
            signInBtn.setPreferredSize(new Dimension(310, 44));
            signInBtn.addActionListener(e -> performLogin());
            rightForm.add(signInBtn, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(8, 50, 0, 50);
            JLabel hint = new JLabel("Default credentials pre-filled: admin / admin123", SwingConstants.CENTER);
            hint.setFont(new Font("Segoe UI", Font.ITALIC, 11));
            hint.setForeground(TEXT_MUTED);
            rightForm.add(hint, gbc);

            // Enter key triggers login
            KeyAdapter enterListener = new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) performLogin();
                }
            };
            userField.addKeyListener(enterListener);
            passField.addKeyListener(enterListener);

            root.add(leftHero);
            root.add(rightForm);
            setContentPane(root);
        }

        private void performLogin() {
            String u = userField.getText().trim();
            String p = new String(passField.getPassword()).trim();

            if (u.isEmpty() || p.isEmpty()) {
                errorLabel.setText("Please enter both username and password.");
                return;
            }
            if (u.equals("admin") && p.equals("admin123")) {
                dispose(); // Close Login
                new DashboardWindow().setVisible(true); // Open Dashboard
            } else {
                errorLabel.setText("Invalid credentials! Use admin / admin123");
                passField.setText("");
            }
        }
    }

    // =========================================================================
    // 2. SAAS DASHBOARD WINDOW
    // =========================================================================
    static class DashboardWindow extends JFrame {
        private CardLayout cardLayout;
        private JPanel mainContent;
        private JLabel headerTitle;
        private JLabel headerSubtitle;
        private NavButton activeNavBtn;

        public DashboardWindow() {
            setTitle("Smart Student Academic & Attendance Management System");
            setSize(1260, 760);
            setMinimumSize(new Dimension(1100, 680));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel root = new JPanel(new BorderLayout());
            root.add(buildSidebar(), BorderLayout.WEST);

            JPanel centerWrapper = new JPanel(new BorderLayout());
            centerWrapper.setBackground(BG_CANVAS);
            centerWrapper.add(buildTopHeader(), BorderLayout.NORTH);

            cardLayout = new CardLayout();
            mainContent = new JPanel(cardLayout);
            mainContent.setBackground(BG_CANVAS);

            mainContent.add(buildDashboardOverview(), "DASHBOARD");
            mainContent.add(buildComingSoonModule("Student Directory & CRUD Module", "Add, edit, filter, and search student profiles (Step 3)"), "STUDENTS");
            mainContent.add(buildComingSoonModule("Daily Attendance Tracker", "Mark Present/Absent and auto-highlight <75% attendance (Step 5)"), "ATTENDANCE");
            mainContent.add(buildComingSoonModule("Academic Marks & Grading", "Subject-wise marks entry and automated GPA/Grade calculation (Step 6)"), "MARKS");
            mainContent.add(buildComingSoonModule("Reports & Export Center", "Generate printable PDF report cards and CSV analytics (Step 7)"), "REPORTS");

            centerWrapper.add(mainContent, BorderLayout.CENTER);
            root.add(centerWrapper, BorderLayout.CENTER);

            setContentPane(root);
        }

        private JPanel buildSidebar() {
            JPanel sidebar = new JPanel(new BorderLayout());
            sidebar.setPreferredSize(new Dimension(255, 0));
            sidebar.setBackground(BG_SIDEBAR);

            // Top Brand Header
            JPanel brandBox = new JPanel(new BorderLayout(12, 0));
            brandBox.setBackground(BG_SIDEBAR);
            brandBox.setBorder(new EmptyBorder(24, 22, 24, 20));

            JLabel badgeIcon = new JLabel("S", SwingConstants.CENTER) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    GradientPaint gp = new GradientPaint(0, 0, ACCENT_INDIGO, getWidth(), getHeight(), PRIMARY_PURPLE);
                    g2.setPaint(gp);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                    g2.dispose();
                    super.paintComponent(g);
                }
            };
            badgeIcon.setPreferredSize(new Dimension(40, 40));
            badgeIcon.setFont(new Font("Segoe UI", Font.BOLD, 20));
            badgeIcon.setForeground(Color.WHITE);

            JPanel brandText = new JPanel(new GridLayout(2, 1));
            brandText.setOpaque(false);
            JLabel bTitle = new JLabel("SSMS CLOUD");
            bTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));
            bTitle.setForeground(Color.WHITE);
            JLabel bSub = new JLabel("II B.Tech • Sem I");
            bSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            bSub.setForeground(new Color(170, 155, 205));
            brandText.add(bTitle);
            brandText.add(bSub);

            brandBox.add(badgeIcon, BorderLayout.WEST);
            brandBox.add(brandText, BorderLayout.CENTER);
            sidebar.add(brandBox, BorderLayout.NORTH);

            // Navigation Menu
            JPanel navList = new JPanel();
            navList.setLayout(new BoxLayout(navList, BoxLayout.Y_AXIS));
            navList.setBackground(BG_SIDEBAR);
            navList.setBorder(new EmptyBorder(10, 14, 10, 14));

            JLabel menuSection = new JLabel("  MAIN NAVIGATION");
            menuSection.setFont(new Font("Segoe UI", Font.BOLD, 10));
            menuSection.setForeground(new Color(125, 110, 160));
            menuSection.setAlignmentX(Component.LEFT_ALIGNMENT);
            navList.add(menuSection);
            navList.add(Box.createVerticalStrut(10));

            NavButton btnDash = new NavButton("◈   Dashboard Overview", "DASHBOARD", "Academic Command Center", "Real-time attendance, marks & institutional KPIs");
            NavButton btnStud = new NavButton("◉   Student Management", "STUDENTS", "Student Directory", "Manage enrollments, profiles & department filters");
            NavButton btnAtt  = new NavButton("▣   Attendance Tracking", "ATTENDANCE", "Attendance Management", "Record daily presence and monitor <75% alerts");
            NavButton btnMark = new NavButton("★   Academic Marks", "MARKS", "Academic Marks & Grades", "Enter subject scores and compute automated grades");
            NavButton btnRep  = new NavButton("▤   Reports & Exports", "REPORTS", "Reports & Export Studio", "Download student report cards in PDF & CSV formats");

            setActiveNav(btnDash);

            navList.add(btnDash);
            navList.add(Box.createVerticalStrut(6));
            navList.add(btnStud);
            navList.add(Box.createVerticalStrut(6));
            navList.add(btnAtt);
            navList.add(Box.createVerticalStrut(6));
            navList.add(btnMark);
            navList.add(Box.createVerticalStrut(6));
            navList.add(btnRep);

            sidebar.add(navList, BorderLayout.CENTER);

            // Bottom Logout Card
            JPanel footer = new JPanel(new BorderLayout());
            footer.setBackground(BG_SIDEBAR);
            footer.setBorder(new EmptyBorder(16, 18, 24, 18));

            JButton logoutBtn = new JButton("⎋  Sign Out of Session") {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getModel().isRollover() ? new Color(195, 40, 70) : new Color(65, 22, 52));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                    g2.dispose();
                    super.paintComponent(g);
                }
            };
            logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
            logoutBtn.setForeground(new Color(255, 210, 220));
            logoutBtn.setContentAreaFilled(false);
            logoutBtn.setBorderPainted(false);
            logoutBtn.setFocusPainted(false);
            logoutBtn.setPreferredSize(new Dimension(0, 42));
            logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            logoutBtn.addActionListener(e -> {
                dispose();
                new LoginWindow().setVisible(true);
            });

            footer.add(logoutBtn, BorderLayout.CENTER);
            sidebar.add(footer, BorderLayout.SOUTH);

            return sidebar;
        }

        // Custom Sidebar Button with Active Left Indicator Bar
        class NavButton extends JButton {
            private boolean active = false;
            private boolean hovered = false;

            public NavButton(String label, String targetCard, String title, String subtitle) {
                super(label);
                setFont(new Font("Segoe UI", Font.BOLD, 13));
                setForeground(new Color(195, 185, 220));
                setHorizontalAlignment(SwingConstants.LEFT);
                setContentAreaFilled(false);
                setBorderPainted(false);
                setFocusPainted(false);
                setBorder(new EmptyBorder(12, 16, 12, 16));
                setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
                setAlignmentX(Component.LEFT_ALIGNMENT);
                setCursor(new Cursor(Cursor.HAND_CURSOR));

                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) { hovered = true; repaint(); }
                    @Override
                    public void mouseExited(MouseEvent e)  { hovered = false; repaint(); }
                });

                addActionListener(e -> {
                    setActiveNav(this);
                    headerTitle.setText(title);
                    headerSubtitle.setText(subtitle);
                    cardLayout.show(mainContent, targetCard);
                });
            }

            public void setActive(boolean a) {
                this.active = a;
                setForeground(a ? Color.WHITE : new Color(195, 185, 220));
                repaint();
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (active) {
                    g2.setColor(PRIMARY_PURPLE);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                    // Glowing left pill bar
                    g2.setColor(new Color(220, 185, 255));
                    g2.fillRoundRect(4, 10, 4, getHeight() - 20, 4, 4);
                } else if (hovered) {
                    g2.setColor(new Color(45, 24, 78));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                }
                g2.dispose();
                super.paintComponent(g);
            }
        }

        private void setActiveNav(NavButton btn) {
            if (activeNavBtn != null) activeNavBtn.setActive(false);
            activeNavBtn = btn;
            activeNavBtn.setActive(true);
        }

        private JPanel buildTopHeader() {
            JPanel header = new JPanel(new BorderLayout());
            header.setBackground(CARD_WHITE);
            header.setPreferredSize(new Dimension(0, 76));
            header.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_LIGHT),
                    new EmptyBorder(12, 28, 12, 28)
            ));

            JPanel titleBlock = new JPanel(new GridLayout(2, 1, 0, 2));
            titleBlock.setOpaque(false);
            headerTitle = new JLabel("Academic Command Center");
            headerTitle.setFont(new Font("Segoe UI", Font.BOLD, 21));
            headerTitle.setForeground(TEXT_DARK);

            headerSubtitle = new JLabel("Real-time attendance, marks & institutional KPIs");
            headerSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            headerSubtitle.setForeground(TEXT_MUTED);

            titleBlock.add(headerTitle);
            titleBlock.add(headerSubtitle);
            header.add(titleBlock, BorderLayout.WEST);

            // Right Profile Pill
            JPanel profilePill = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 6)) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(BG_CANVAS);
                    g2.fillRoundRect(0, 4, getWidth(), getHeight() - 8, 24, 24);
                    g2.dispose();
                    super.paintComponent(g);
                }
            };
            profilePill.setOpaque(false);

            JLabel statusDot = new JLabel("● Connected");
            statusDot.setFont(new Font("Segoe UI", Font.BOLD, 12));
            statusDot.setForeground(EMERALD_GREEN);

            JLabel adminRole = new JLabel("Administrator  |  AY 2026–27  ");
            adminRole.setFont(new Font("Segoe UI", Font.BOLD, 12));
            adminRole.setForeground(TEXT_DARK);

            profilePill.add(statusDot);
            profilePill.add(adminRole);
            header.add(profilePill, BorderLayout.EAST);

            return header;
        }

        // =====================================================================
        // DASHBOARD OVERVIEW CONTENT (KPI Cards + 2D Charts + Styled Table)
        // =====================================================================
        private JPanel buildDashboardOverview() {
            JPanel container = new JPanel(new BorderLayout(18, 18));
            container.setBackground(BG_CANVAS);
            container.setBorder(new EmptyBorder(22, 28, 22, 28));

            // 1. TOP KPI ROW (4 Cards)
            JPanel kpiGrid = new JPanel(new GridLayout(1, 4, 18, 0));
            kpiGrid.setOpaque(false);
            kpiGrid.add(createKpiCard("TOTAL ENROLLED", "240", "+18 new this semester", PRIMARY_PURPLE, "STU"));
            kpiGrid.add(createKpiCard("AVG ATTENDANCE", "87.6%", "Above 75% mandatory norm", EMERALD_GREEN, "ATT"));
            kpiGrid.add(createKpiCard("MEAN ACADEMIC CGPA", "8.42", "Equivalent to 79.5% avg", ACCENT_INDIGO, "GPA"));
            kpiGrid.add(createKpiCard("BELOW 75% ALERT", "11", "Requires faculty follow-up", CRIMSON_RED, "LOW"));
            container.add(kpiGrid, BorderLayout.NORTH);

            // 2. MIDDLE CHARTS ROW (Bar Chart + Donut Chart)
            JPanel chartsRow = new JPanel(new GridLayout(1, 2, 18, 0));
            chartsRow.setOpaque(false);
            chartsRow.add(createDepartmentBarChartCard());
            chartsRow.add(createGradeDonutChartCard());
            container.add(chartsRow, BorderLayout.CENTER);

            // 3. BOTTOM TABLE CARD (Top Performers & Attendance Watchlist)
            JPanel tableCard = createRoundedCard();
            tableCard.setLayout(new BorderLayout(0, 10));
            tableCard.setPreferredSize(new Dimension(0, 185));

            JPanel tblHeader = new JPanel(new BorderLayout());
            tblHeader.setOpaque(false);
            JLabel tblTitle = new JLabel("Top Academic Performers & Attendance Watchlist");
            tblTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
            tblTitle.setForeground(TEXT_DARK);

            JLabel tblBadge = new JLabel("Live Institutional Snapshot");
            tblBadge.setFont(new Font("Segoe UI", Font.BOLD, 11));
            tblBadge.setForeground(PRIMARY_PURPLE);
            tblHeader.add(tblTitle, BorderLayout.WEST);
            tblHeader.add(tblBadge, BorderLayout.EAST);
            tableCard.add(tblHeader, BorderLayout.NORTH);

            String[] cols = {"ROLL NUMBER", "STUDENT NAME", "DEPARTMENT", "YEAR", "ATTENDANCE", "AVG MARKS", "STANDING"};
            Object[][] rows = {
                    {"25BQ1A0401", "Ananya Sharma", "ECE", "II Year", "96.4%", "94.8%", "Distinction (S)"},
                    {"25BQ1A0512", "Karthikeya Reddy", "CSE", "II Year", "93.0%", "91.5%", "Distinction (S)"},
                    {"25BQ1A0428", "Rahul Rufus", "ECE", "II Year", "91.2%", "89.6%", "First Class (A+)"},
                    {"25BQ1A0209", "Sneha Nair", "EEE", "II Year", "71.4%", "76.0%", "Below 75% Alert"}
            };

            JTable table = new JTable(rows, cols);
            table.setRowHeight(29);
            table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            table.setShowVerticalLines(false);
            table.setGridColor(new Color(238, 234, 246));
            table.setSelectionBackground(new Color(236, 225, 252));
            table.setSelectionForeground(TEXT_DARK);

            JTableHeader th = table.getTableHeader();
            th.setFont(new Font("Segoe UI", Font.BOLD, 11));
            th.setBackground(new Color(244, 240, 252));
            th.setForeground(TEXT_MUTED);
            th.setPreferredSize(new Dimension(0, 32));

            // Custom Renderer to highlight <75% Alert in Red & Distinction in Green
            DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable t, Object val, boolean isSel, boolean hasFoc, int r, int c) {
                    Component comp = super.getTableCellRendererComponent(t, val, isSel, hasFoc, r, c);
                    setBorder(new EmptyBorder(0, 10, 0, 10));
                    String text = String.valueOf(val);
                    if (c == 6 || c == 4) {
                        if (text.contains("Below") || text.startsWith("71")) {
                            comp.setForeground(CRIMSON_RED);
                            comp.setFont(new Font("Segoe UI", Font.BOLD, 13));
                        } else if (text.contains("Distinction") || text.startsWith("9")) {
                            comp.setForeground(EMERALD_GREEN);
                            comp.setFont(new Font("Segoe UI", Font.BOLD, 13));
                        } else {
                            comp.setForeground(TEXT_DARK);
                        }
                    } else {
                        comp.setForeground(TEXT_DARK);
                    }
                    return comp;
                }
            };
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
            }

            JScrollPane sp = new JScrollPane(table);
            sp.setBorder(BorderFactory.createLineBorder(BORDER_LIGHT));
            sp.getViewport().setBackground(CARD_WHITE);
            tableCard.add(sp, BorderLayout.CENTER);

            container.add(tableCard, BorderLayout.SOUTH);
            return container;
        }

        private JPanel createKpiCard(String label, String bigValue, String sub, Color accent, String badgeText) {
            JPanel card = new JPanel(new BorderLayout(0, 6)) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(CARD_WHITE);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);

                    // Top Accent Bar
                    g2.setColor(accent);
                    g2.fillRoundRect(0, 0, getWidth(), 5, 8, 8);

                    g2.setColor(BORDER_LIGHT);
                    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                    g2.dispose();
                }
            };
            card.setOpaque(false);
            card.setBorder(new EmptyBorder(16, 20, 14, 20));
            card.setPreferredSize(new Dimension(0, 118));

            JPanel topRow = new JPanel(new BorderLayout());
            topRow.setOpaque(false);
            JLabel lbl = new JLabel(label);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
            lbl.setForeground(TEXT_MUTED);

            JLabel pill = new JLabel(" " + badgeText + " ");
            pill.setFont(new Font("Segoe UI", Font.BOLD, 10));
            pill.setForeground(accent);
            pill.setBorder(BorderFactory.createLineBorder(accent, 1, true));

            topRow.add(lbl, BorderLayout.WEST);
            topRow.add(pill, BorderLayout.EAST);

            JLabel val = new JLabel(bigValue);
            val.setFont(new Font("Segoe UI", Font.BOLD, 30));
            val.setForeground(TEXT_DARK);

            JLabel bottomSub = new JLabel(sub);
            bottomSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            bottomSub.setForeground(accent);

            card.add(topRow, BorderLayout.NORTH);
            card.add(val, BorderLayout.CENTER);
            card.add(bottomSub, BorderLayout.SOUTH);
            return card;
        }

        // Custom Java 2D Multi-Bar Chart (Attendance vs Average Marks)
        private JPanel createDepartmentBarChartCard() {
            JPanel card = createRoundedCard();
            card.setLayout(new BorderLayout());

            JPanel top = new JPanel(new BorderLayout());
            top.setOpaque(false);
            JLabel title = new JLabel("Department Attendance vs. Academic Score (%)");
            title.setFont(new Font("Segoe UI", Font.BOLD, 15));
            title.setForeground(TEXT_DARK);

            JLabel legend = new JLabel("■ Attendance %    ■ Marks %");
            legend.setFont(new Font("Segoe UI", Font.BOLD, 11));
            legend.setForeground(PRIMARY_PURPLE);
            top.add(title, BorderLayout.WEST);
            top.add(legend, BorderLayout.EAST);
            card.add(top, BorderLayout.NORTH);

            JPanel canvas = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    String[] depts = {"ECE", "CSE", "EEE", "IT", "MECH"};
                    int[] att = {91, 89, 83, 87, 78};
                    int[] mrk = {86, 88, 79, 84, 74};

                    int w = getWidth();
                    int h = getHeight();
                    int baseLine = h - 32;
                    int maxH = h - 68;

                    // Horizontal Grid Lines
                    g2.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                    for (int pct = 25; pct <= 100; pct += 25) {
                        int y = baseLine - (int) ((pct / 100.0) * maxH);
                        g2.setColor(new Color(238, 234, 246));
                        g2.drawLine(38, y, w - 15, y);
                        g2.setColor(TEXT_MUTED);
                        g2.drawString(pct + "%", 6, y + 4);
                    }

                    // 75% Mandatory Attendance Threshold Dashed Line
                    int y75 = baseLine - (int) (0.75 * maxH);
                    Stroke oldStroke = g2.getStroke();
                    g2.setColor(CRIMSON_RED);
                    g2.setStroke(new BasicStroke(1.2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, new float[]{5f}, 0f));
                    g2.drawLine(38, y75, w - 15, y75);
                    g2.setStroke(oldStroke);

                    int groupWidth = (w - 60) / depts.length;
                    int barW = 22;

                    for (int i = 0; i < depts.length; i++) {
                        int cx = 48 + i * groupWidth + (groupWidth / 2);

                        // Bar 1: Attendance (Primary Purple)
                        int h1 = (int) ((att[i] / 100.0) * maxH);
                        g2.setColor(PRIMARY_PURPLE);
                        g2.fillRoundRect(cx - barW - 3, baseLine - h1, barW, h1, 8, 8);

                        // Bar 2: Marks (Soft Indigo/Lavender)
                        int h2 = (int) ((mrk[i] / 100.0) * maxH);
                        g2.setColor(new Color(165, 115, 230));
                        g2.fillRoundRect(cx + 3, baseLine - h2, barW, h2, 8, 8);

                        // Value Labels
                        g2.setFont(new Font("Segoe UI", Font.BOLD, 10));
                        g2.setColor(TEXT_DARK);
                        g2.drawString(att[i] + "%", cx - barW - 3, baseLine - h1 - 5);
                        g2.drawString(mrk[i] + "%", cx + 3, baseLine - h2 - 5);

                        // Dept Label
                        g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                        g2.setColor(TEXT_MUTED);
                        int dw = g2.getFontMetrics().stringWidth(depts[i]);
                        g2.drawString(depts[i], cx - dw / 2, baseLine + 18);
                    }
                    g2.dispose();
                }
            };
            canvas.setOpaque(false);
            card.add(canvas, BorderLayout.CENTER);
            return card;
        }

        // Custom Java 2D Ring / Donut Chart for Academic Grades
        private JPanel createGradeDonutChartCard() {
            JPanel card = createRoundedCard();
            card.setLayout(new BorderLayout());

            JLabel title = new JLabel("Academic Grade Distribution (II B.Tech)");
            title.setFont(new Font("Segoe UI", Font.BOLD, 15));
            title.setForeground(TEXT_DARK);
            card.add(title, BorderLayout.NORTH);

            JPanel canvas = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    String[] labels = {"Grade S  (>=90%)", "Grade A  (80-89%)", "Grade B  (70-79%)", "Grade C  (<70%)"};
                    int[] pcts = {28, 42, 20, 10};
                    Color[] cols = {EMERALD_GREEN, PRIMARY_PURPLE, ACCENT_INDIGO, AMBER_GOLD};

                    int size = Math.min(getWidth() / 2 - 20, getHeight() - 30);
                    size = Math.max(size, 130);
                    int x = 25;
                    int y = (getHeight() - size) / 2;

                    double startAngle = 90;
                    for (int i = 0; i < pcts.length; i++) {
                        double extent = -(pcts[i] / 100.0) * 360.0;
                        g2.setColor(cols[i]);
                        g2.fill(new Arc2D.Double(x, y, size, size, startAngle, extent, Arc2D.PIE));
                        startAngle += extent;
                    }

                    // Cut out inner circle for Donut look
                    int hole = (int) (size * 0.58);
                    int hx = x + (size - hole) / 2;
                    int hy = y + (size - hole) / 2;
                    g2.setColor(CARD_WHITE);
                    g2.fillOval(hx, hy, hole, hole);

                    // Center Donut Label
                    g2.setColor(TEXT_DARK);
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 18));
                    String centerTxt = "95.4%";
                    int cw = g2.getFontMetrics().stringWidth(centerTxt);
                    g2.drawString(centerTxt, x + (size - cw) / 2, y + size / 2 + 2);

                    g2.setColor(TEXT_MUTED);
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 10));
                    String passTxt = "PASS RATE";
                    int pw = g2.getFontMetrics().stringWidth(passTxt);
                    g2.drawString(passTxt, x + (size - pw) / 2, y + size / 2 + 16);

                    // Right Legend List
                    int lx = x + size + 32;
                    int ly = y + 18;
                    for (int i = 0; i < labels.length; i++) {
                        g2.setColor(cols[i]);
                        g2.fillRoundRect(lx, ly + i * 34, 14, 14, 4, 4);

                        g2.setColor(TEXT_DARK);
                        g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                        g2.drawString(labels[i], lx + 22, ly + i * 34 + 12);

                        g2.setColor(TEXT_MUTED);
                        g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                        g2.drawString(pcts[i] + "% of students", lx + 140, ly + i * 34 + 12);
                    }
                    g2.dispose();
                }
            };
            canvas.setOpaque(false);
            card.add(canvas, BorderLayout.CENTER);
            return card;
        }

        private JPanel createRoundedCard() {
            JPanel card = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(CARD_WHITE);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                    g2.setColor(BORDER_LIGHT);
                    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                    g2.dispose();
                }
            };
            card.setOpaque(false);
            card.setBorder(new EmptyBorder(16, 20, 16, 20));
            return card;
        }

        private JPanel buildComingSoonModule(String title, String sub) {
            JPanel wrap = new JPanel(new GridBagLayout());
            wrap.setBackground(BG_CANVAS);
            JPanel card = createRoundedCard();
            card.setPreferredSize(new Dimension(520, 180));
            card.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(6, 15, 6, 15);

            JLabel t = new JLabel(title);
            t.setFont(new Font("Segoe UI", Font.BOLD, 20));
            t.setForeground(PRIMARY_PURPLE);
            card.add(t, gbc);

            gbc.gridy++;
            JLabel s = new JLabel(sub);
            s.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            s.setForeground(TEXT_MUTED);
            card.add(s, gbc);

            wrap.add(card);
            return wrap;
        }
    }

    // =========================================================================
    // 3. REUSABLE STYLED INPUT & BUTTON HELPERS
    // =========================================================================
    private static JTextField createStyledTextField(String defaultText) {
        JTextField tf = new JTextField(defaultText, 18) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(CARD_WHITE);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10));
                g2.setColor(isFocusOwner() ? PRIMARY_PURPLE : BORDER_LIGHT);
                g2.setStroke(new BasicStroke(isFocusOwner() ? 1.8f : 1.0f));
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        tf.setOpaque(false);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setForeground(TEXT_DARK);
        tf.setBorder(new EmptyBorder(10, 14, 10, 14));
        tf.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { tf.repaint(); }
            @Override public void focusLost(FocusEvent e)   { tf.repaint(); }
        });
        return tf;
    }

    private static JPasswordField createStyledPasswordField(String defaultPass) {
        JPasswordField pf = new JPasswordField(defaultPass, 18) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(CARD_WHITE);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10));
                g2.setColor(isFocusOwner() ? PRIMARY_PURPLE : BORDER_LIGHT);
                g2.setStroke(new BasicStroke(isFocusOwner() ? 1.8f : 1.0f));
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        pf.setOpaque(false);
        pf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pf.setForeground(TEXT_DARK);
        pf.setBorder(new EmptyBorder(10, 14, 10, 14));
        pf.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { pf.repaint(); }
            @Override public void focusLost(FocusEvent e)   { pf.repaint(); }
        });
        return pf;
    }

    private static JButton createPrimaryButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(
                        0, 0, getModel().isRollover() ? ACCENT_INDIGO : PRIMARY_PURPLE,
                        getWidth(), getHeight(), BG_GRADIENT_TOP
                );
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
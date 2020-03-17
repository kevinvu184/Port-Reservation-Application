package view.panel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.Menu;
import view.MainFrame;

@SuppressWarnings("serial")
public class DatabasePanel extends JPanel {

	private Menu model;
	private JTextArea summary = new JTextArea(21, 90);

	public DatabasePanel(MainFrame mf) {
		model = mf.getModel();
		summary.setEditable(false);
		summary.setLineWrap(true);
		summary.setFont(new Font("Consolas", Font.PLAIN, 12));

		JPanel summaryPanel = new JPanel();
		summaryPanel.setLayout(new BorderLayout());
		summaryPanel.setBorder(new CompoundBorder(new EtchedBorder(), new TitledBorder("Database")));

		JScrollPane summaryPane = new JScrollPane(summary, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		summaryPanel.add(summaryPane, BorderLayout.CENTER);

		add(summaryPanel, BorderLayout.CENTER);

		updateDatabase(model);
	}

	public void updateDatabase(Menu model) {
		String detail = "StudentID\t\tStudentName\t\tPort\t\tEmail\n";
		if (!model.preCheckFileAndSetUp()) {
			model.getPortRun().setFirstRunDatabase();
		}
		detail += model.getPortRun().getDatabase();
		summary.setText(detail);
	}
}

package pe.edu.tecsup.app.view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import pe.edu.tecsup.app.utils.ConexionDB;

public class AplicacionSimpleGUI {

	private JFrame frame;

	private JComboBox<Integer> cmbIdsCategorias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicacionSimpleGUI window = new AplicacionSimpleGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AplicacionSimpleGUI() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {

		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// cargarDatos();
			}
		});
		frame.setBounds(100, 100, 385, 226);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Label
		JLabel lblCategoriaId = new JLabel("Categoria Id : ");
		lblCategoriaId.setBounds(75, 33, 90, 15);
		frame.getContentPane().add(lblCategoriaId);

		// Combo
		this.cmbIdsCategorias = new JComboBox<Integer>();

		cmbIdsCategorias.setBounds(162, 29, 127, 25);
		frame.getContentPane().add(cmbIdsCategorias);

		// Button report categorias
		JButton btnGenerateRptCategoria = new JButton("Reporte categorias");
		btnGenerateRptCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				generarReporteCategoria();
			}
		});

		btnGenerateRptCategoria.setBounds(20, 100, 170, 25);
		frame.getContentPane().add(btnGenerateRptCategoria);

		// Button report productos
		JButton btnGenerateRptProductos = new JButton("Reporte productos");
		btnGenerateRptProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				generarReporteProductos();
			}
		});

		btnGenerateRptProductos.setBounds(200, 100, 170, 25);
		frame.getContentPane().add(btnGenerateRptProductos);

	}

	protected void generarReporteCategoria() {
		// TODO Auto-generated method stub
		try {

			Connection con = ConexionDB.getConexion();

			Map<String, Object> parameters = new HashMap<>();
			JasperReport report = JasperCompileManager.compileReport("jasper/categorias.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, parameters, con);
			JRViewer jv = new JRViewer(print);
			final JFrame jf = new JFrame();
			jf.getContentPane().add(jv);
			jf.validate();
			jf.setVisible(true);
			jf.setExtendedState(jf.getExtendedState() | JFrame.MAXIMIZED_BOTH);
			jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

			WindowListener exitListener = new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					// super.windowClosing(e);
					jf.dispose();
				}
			};

			jf.addWindowListener(exitListener);

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	protected void generarReporteProductos() {
		
	}

}

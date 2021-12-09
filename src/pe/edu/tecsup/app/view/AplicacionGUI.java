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
import pe.edu.tecsup.app.services.CategoriaService;
import pe.edu.tecsup.app.utils.ConexionDB;

public class AplicacionGUI {

	private JFrame frame;

	private JComboBox<Integer> cmbIdsCategorias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicacionGUI window = new AplicacionGUI();
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
	public AplicacionGUI() throws Exception {
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
		CategoriaService categoriaService = new CategoriaService();
		Integer[] catIds = categoriaService.listarIds();
		this.cmbIdsCategorias = new JComboBox<Integer>(catIds);

		cmbIdsCategorias.setBounds(162, 29, 127, 25);
		frame.getContentPane().add(cmbIdsCategorias);

		// Button report
		JButton btnGenerateRptCategoria = new JButton("Generar reporte total");
		btnGenerateRptCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				generarReporteCategoria();
			}
		});

		btnGenerateRptCategoria.setBounds(20, 100, 170, 25);
		frame.getContentPane().add(btnGenerateRptCategoria);

		// Button report por Id
		JButton btnGenerateRptCategoriaPorId = new JButton("Generar reporte por Id");
		btnGenerateRptCategoriaPorId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				generarReporteCategoriaPorId();
			}
		});

		btnGenerateRptCategoriaPorId.setBounds(200, 100, 170, 25);
		frame.getContentPane().add(btnGenerateRptCategoriaPorId);

	}

	protected void generarReporteCategoriaPorId() {
		// TODO Auto-generated method stub
		try {

			Connection con = ConexionDB.getConexion();

			Map<String, Object> parameters = new HashMap<>();
			
			String id = this.cmbIdsCategorias.getSelectedItem().toString();
			
			parameters.put("SF_CAT_ID", Integer.valueOf(id));
			
			JasperReport report 
				= JasperCompileManager.compileReport("jasper/categoria_por_id.jrxml");
			
			JasperPrint print 
				= JasperFillManager.fillReport(report, parameters, con);
			
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
					jf.dispose();
				}
			};

			jf.addWindowListener(exitListener);

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void generarReporteCategoria() {
		// TODO Auto-generated method stub
		try {

			Connection con = ConexionDB.getConexion();

			Map<String, Object> parameters = new HashMap<>();

			JasperReport report 
				= JasperCompileManager.compileReport("jasper/categorias.jrxml");
			JasperPrint print 
				= JasperFillManager.fillReport(report, parameters, con);
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
					jf.dispose();
				}
			};

			jf.addWindowListener(exitListener);

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

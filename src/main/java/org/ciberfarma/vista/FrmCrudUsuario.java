package org.ciberfarma.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmCrudUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textcodigo;
	private JTextField textnombre;
	private JTextField textapellido;
	private JTextField textusuario;
	private JTextField textclave;
	private JTextField textFch;
	private JTextField textTipo;
	private JTextField textEstado;

	private JTextArea textResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		textcodigo = new JTextField();
		textcodigo.setBounds(59, 8, 86, 20);
		contentPane.add(textcodigo);
		textcodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 36, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Usuario:");
		lblNewLabel_3.setBounds(10, 88, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Clave:");
		lblNewLabel_4.setBounds(166, 88, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fch. Nac:");
		lblNewLabel_5.setBounds(10, 117, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		textnombre = new JTextField();
		textnombre.setBounds(59, 33, 227, 20);
		contentPane.add(textnombre);
		textnombre.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 142, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(166, 142, 46, 14);
		contentPane.add(lblEstado);
		
		textapellido = new JTextField();
		textapellido.setBounds(59, 58, 227, 20);
		contentPane.add(textapellido);
		textapellido.setColumns(10);
		
		textusuario = new JTextField();
		textusuario.setBounds(59, 85, 86, 20);
		contentPane.add(textusuario);
		textusuario.setColumns(10);
		
		textclave = new JTextField();
		textclave.setBounds(201, 85, 86, 20);
		contentPane.add(textclave);
		textclave.setColumns(10);
		
		textFch = new JTextField();
		textFch.setBounds(59, 114, 86, 20);
		contentPane.add(textFch);
		textFch.setColumns(10);
		
		textTipo = new JTextField();
		textTipo.setBounds(59, 139, 86, 20);
		contentPane.add(textTipo);
		textTipo.setColumns(10);
		
		textEstado = new JTextField();
		textEstado.setBounds(201, 139, 86, 20);
		contentPane.add(textEstado);
		textEstado.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 registro();
			}
		});
		btnRegistrar.setBounds(309, 21, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnConsultar.setBounds(309, 57, 89, 23);
		contentPane.add(btnConsultar);
		
		JButton btnListado = new JButton("listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LISTADO();
			}
		});
		btnListado.setBounds(309, 88, 89, 23);
		contentPane.add(btnListado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 325, 224);
		contentPane.add(scrollPane);
		
		textResultado = new JTextArea();
		scrollPane.setViewportView(textResultado);
	}
	  void LISTADO() {
		//OBTENER UN LISTADI DE LOS USUARIO 
		  EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("dawi_sesion04");
			EntityManager em = fabrica.createEntityManager();
		//List<Usuario> lstUsuario = em.createNamedQuery("Usuario.findAll",Usuario.class).getResultList();
		       List  <Usuario>  lstUsuario;  
		// muestro el listado en el txt/pdf
		       	if(textTipo.getText().isEmpty()) {
		       		lstUsuario=em.createNamedQuery("Usuario.findAll",Usuario.class).getResultList();
		       		
		       				
		       	}else {
		       		int tipo=Integer.parseInt(textTipo.getText());
		       		lstUsuario=em.createNamedQuery("Usuario.findAllxTipo",Usuario.class).
		       				setParameter("xtipo", tipo).getResultList();
		       	}
		       	
		       	textResultado.setText("Listado de USUARIO\n");
				for (Usuario u : lstUsuario) {
					textResultado.append(u.getCodigo() + "\t" + u.getNombre() + "\t" + u.getApellido() +"\n");
				}
		
	}

	void consultar() {
		//obtener el codifo a buscar
		int codigo = Integer.parseInt(textcodigo.getText());
		//buscar el codigo en los usuarios(Endidad), si existe muestra los datos, sinos avisa
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("dawi_sesion04");
		EntityManager em = fabrica.createEntityManager();
		Usuario u= em.find(Usuario.class, codigo);
		if(u==null) {
			JOptionPane.showMessageDialog(this, "Usuario NO registrado");
		}else {
			textnombre.setText(u.getNombre());
			textapellido.setText(u.getApellido());
			textusuario.setText(u.getUsuario());
			textclave.setText(u.getClave());
			textFch.setText(u.getFnacim());
			textTipo.setText(u.getTipo()+"");
			textEstado.setText(u.getEstado()+"");
			
		}
	}

	void registro() {
		String Nombre=textnombre.getText();
		String Apellido=textapellido.getText();
		String Usuario=textusuario.getText();
		String Clave=textclave.getText();
		String Fnacim=textFch.getText();
		
		int tipo = Integer.parseInt(textTipo.getText());
		int estado=Integer.parseInt(textEstado.getText());
		Usuario u = new Usuario();
		//u.setCodigo(10);int auto_increment
		u.setNombre(Nombre);
		u.setApellido(Apellido);
		u.setUsuario(Usuario);
		u.setClave(Clave);
		u.setFnacim(Fnacim);
		u.setTipo(tipo);
		u.setEstado(estado);
		
		// grabar el objeto
		// 1. fabricar el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("dawi_sesion04");
		// 2. crear el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		// 3. empezar mi transacci�n
		try {
			em.getTransaction().begin();
			// proceso a realizar (persistencia)
			em.persist(u);
			//em.merge(u);
			// 4. confirmar la transacci�n
			
			em.getTransaction().commit();
			JOptionPane.showMessageDialog(this, "Usuario registrado");
		} catch (HeadlessException e) {
			JOptionPane.showMessageDialog(this, "Error al registrar:"+e.getMessage());
		}
		finally {
		//cerrar
		em.close();
		}	
	}
}

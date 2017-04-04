package com.semm.core.bd.hibernate;

import org.hibernate.Session;

import com.semm.core.bd.*;
import com.semm.core.bd.trivia.ParticipanteDAO;
import com.semm.core.bd.trivia.PreguntaDAO;
import com.semm.core.servicios.*;
import com.semm.core.servicios.cvmed.*;
import com.semm.core.servicios.trivia.*;
import com.semm.core.conexiones.*;


public class FabricaHibernate extends FabricaDAO {
	
	
	@Override
	public MensajeDAO getMensajeDAO() {
		return (MensajeDAO)instantiateDAO(MensajeDAOHibernate.class);
	}

	

    private HibernateGenericoDAO instantiateDAO(Class daoClass) {
        try {
        	HibernateGenericoDAO dao = (HibernateGenericoDAO)daoClass.newInstance();
            dao.setSession(getCurrentSession());
            return dao;
        } catch (Exception ex) {
            throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);
        }
    }

    // You could override this if you don't want HibernateUtil for lookup
    protected Session getCurrentSession() {
        return HibernateSessionFactory.currentSession();
    }

    // Inline concrete DAO implementations with no business-related data access methods.
    // If we use public static nested classes, we can centralize all of them in one source file.

    public static class SesionLogDAOHibernate
    extends HibernateGenericoDAO<SesionLog, String>
    implements SesionLogDAO {}

    
    public static class MedicoDAOHibernate
    extends HibernateGenericoDAO<Medico, Integer>
    implements MedicoDAO {}
    
    public static class FarmaciaDAOHibernate
    extends HibernateGenericoDAO<Farmacia, Integer>
    implements FarmaciaDAO {}
    
    public static class ProductoDAOHibernate
    extends HibernateGenericoDAO<Producto, String>
    implements ProductoDAO {}
    
    public static class RepresentanteDAOHibernate
    extends HibernateGenericoDAO<Representante, String>
    implements RepresentanteDAO {}
    
    public static class RepresentanteFarmaciaDAOHibernate
    extends HibernateGenericoDAO<RepresentanteFarmacia, String>
    implements RepresentanteFarmaciaDAO {}
    
    public static class GptDAOHibernate
    extends HibernateGenericoDAO<Gpt, String>
    implements GptDAO {}
    
    public static class ParticipanteDAOHibernate
    extends HibernateGenericoDAO<Participante, String>
    implements ParticipanteDAO {}
    
    public static class PreguntaDAOHibernate
    extends HibernateGenericoDAO<Pregunta, Long>
    implements PreguntaDAO {}

    public static class MensajePorProcesarDAOHibernate
    extends HibernateGenericoDAO<MensajePorProcesar, Long>
    implements MensajePorProcesarDAO {}

    
   

	@Override
	public TxDAO getTx() {
		return new HibernateTx();
	}

	@Override
	public SesionLogDAO getSesionLogDAO() {
		return (SesionLogDAO)instantiateDAO(SesionLogDAOHibernate.class);
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return (UsuarioDAO)instantiateDAO(UsuarioDAOHibernate.class);
	}

	@Override
	public ConexionDAO getConexionDAO() {
		return (ConexionDAO)instantiateDAO(ConexionDAOHibernate.class);
	}



	@Override
	public ListaDAO getListaDAO() {
		return (ListaDAO)instantiateDAO(ListaDAOHibernate.class);
	}



	@Override
	public ClienteDAO getClienteDAO() {
		return (ClienteDAO)instantiateDAO(ClienteDAOHibernate.class);
	}



	@Override
	public MensajeProgramadoDAO getMensajeProgramadoDAO() {
		return (MensajeProgramadoDAO)instantiateDAO(MensajeProgramadoDAOHibernate.class);
	}



	@Override
	public BloqueadoDAO getBloqueadoDAO() {
		return (BloqueadoDAO)instantiateDAO(BloqueadoDAOHibernate.class);
	}



	@Override
	public GrupoConexionesDAO getGrupoConexionesDAO() {
		return (GrupoConexionesDAO )instantiateDAO(GrupoConexionesDAOHibernate.class);
	}



	@Override
	public MedicoDAO getMedicoDAO() {
		return (MedicoDAO)instantiateDAO(MedicoDAOHibernate.class);
	}

	public VentaDAO getVentaDAO() {
		return (VentaDAO)instantiateDAO(VentaDAOHibernate.class);
	}


	@Override
	public ProductoDAO getProductoDAO() {
		return (ProductoDAO)instantiateDAO(ProductoDAOHibernate.class);
	}



	@Override
	public RepresentanteDAO getRepresentanteDAO() {
		return (RepresentanteDAO )instantiateDAO(RepresentanteDAOHibernate.class);
	}



	@Override
	public VisitaDAO getVisitaDAO() {
		
		return (VisitaDAO )instantiateDAO(VisitaDAOHibernate.class);
	}



	@Override
	public GptDAO getGptDAO() {

		return (GptDAO)instantiateDAO(GptDAOHibernate.class);
	}



	@Override
	public VisitaDAOFarmacia getVisitaDAOFarmacia() {
		return (VisitaDAOFarmacia )instantiateDAO(VisitaDAOFarmaciaHibernate.class);
	}



	@Override
	public FarmaciaDAO getFarmaciaDAO() {
		return (FarmaciaDAO)instantiateDAO(FarmaciaDAOHibernate.class);
	}



	@Override
	public RepresentanteFarmaciaDAO getRepresentanteFarmaciaDAO() {
		return (RepresentanteFarmaciaDAO)instantiateDAO(RepresentanteFarmaciaDAOHibernate.class);
	}



	@Override
	public ParticipanteDAO getParticipanteDAO() {
		return (ParticipanteDAO)instantiateDAO(ParticipanteDAOHibernate.class);
	}



	@Override
	public PreguntaDAO getPreguntaDAO() {
		return (PreguntaDAO)instantiateDAO(PreguntaDAOHibernate.class);
	}


	@Override
	public VProgramadaDAO getVProgramadaDAO() {
		return (VProgramadaDAO)instantiateDAO(VProgramadaDAOHibernate.class);
	}



	@Override
	public ListaDinamicaDAO getListaDinamicaDAO() {
		return (ListaDinamicaDAO)instantiateDAO(ListaDinamicaDAOHibernate.class);
	}

	@Override
	public RestriccionDAO getRestriccionDAO() {
		return (RestriccionDAO)instantiateDAO(RestriccionDAOHibernate.class);
	}

	@Override
	public KeywordSetDAO getKeywordSetDAO() {
		return (KeywordSetDAO)instantiateDAO(KeywordSetDAOHibernate.class);
	}
	
	@Override
	public LlamadaDAO getLlamadaDAO() {
		return (LlamadaDAO)instantiateDAO(LlamadaDAOHibernate.class);
	}
	@Override
	public CompraDAO getCompraDAO() {
		return (CompraDAO)instantiateDAO(CompraDAOHibernate.class);
	}
	@Override
	public MensajePorProcesarDAO getMensajePorProcesarDAO() {
		return (MensajePorProcesarDAO)instantiateDAO(MensajePorProcesarDAOHibernate.class);
	}
}
package com.semm.core.bd;

import com.semm.core.bd.hibernate.FabricaHibernate;
import com.semm.core.bd.trivia.ParticipanteDAO;
import com.semm.core.bd.trivia.PreguntaDAO;

public abstract class FabricaDAO {
	
	
    /**
     * Creates a standalone DAOFactory that returns unmanaged DAO
     * beans for use in any environment Hibernate has been configured
     * for. Uses HibernateUtil/SessionFactory and Hibernate context
     * propagation (CurrentSessionContext), thread-bound or transaction-bound,
     * and transaction scoped.
     */
    public static final Class HIBERNATE = FabricaHibernate.class;

    /**
     * Factory method for instantiation of concrete factories.
     */
    public static FabricaDAO getInstancia(Class factory) {
        try {
            return (FabricaDAO)factory.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create DAOFactory: " + factory);
        }
    }

    // Add your DAO interfaces here
    public abstract MensajeDAO getMensajeDAO();
    public abstract MensajePorProcesarDAO getMensajePorProcesarDAO();
    public abstract UsuarioDAO getUsuarioDAO();
    public abstract ListaDAO getListaDAO();
    public abstract ClienteDAO getClienteDAO();
    public abstract ConexionDAO getConexionDAO();
    public abstract MensajeProgramadoDAO getMensajeProgramadoDAO();
    public abstract SesionLogDAO getSesionLogDAO();
    public abstract TxDAO getTx();
    public abstract BloqueadoDAO getBloqueadoDAO();
    public abstract VisitaDAO getVisitaDAO();
    public abstract VProgramadaDAO getVProgramadaDAO();
    public abstract VisitaDAOFarmacia getVisitaDAOFarmacia();
    public abstract MedicoDAO getMedicoDAO();
    public abstract FarmaciaDAO getFarmaciaDAO();
    public abstract ProductoDAO getProductoDAO();
    public abstract GptDAO getGptDAO();
    public abstract RepresentanteFarmaciaDAO getRepresentanteFarmaciaDAO();
    public abstract RepresentanteDAO getRepresentanteDAO();
    public abstract GrupoConexionesDAO getGrupoConexionesDAO();
    public abstract ParticipanteDAO getParticipanteDAO();
    public abstract PreguntaDAO getPreguntaDAO();
    public abstract LlamadaDAO getLlamadaDAO();
    public abstract KeywordSetDAO getKeywordSetDAO();
    public abstract ListaDinamicaDAO getListaDinamicaDAO();
    public abstract RestriccionDAO getRestriccionDAO();
    public abstract CompraDAO getCompraDAO();
    public abstract VentaDAO getVentaDAO();
    


}
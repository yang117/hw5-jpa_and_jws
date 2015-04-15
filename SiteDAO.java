package hw5;

import java.io.*;
import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.persistence.*;
import javax.print.attribute.standard.Media;

@Path("/api")
public class SiteDao {
	
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("hw6");
	EntityManager em=null;
	
	@GET
	@Path("/site/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("id") int siteId){
		Site site=null;
		
		em=factory.createEntityManager();
		em.getTransaction().begin();
		
		site=em.find(Site.class, siteId);
		
		em.getTransaction().commit();
		em.close();
		
		return site;
		
	}
	
	@GET
	@Path("/site")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> findAllSites() {
		List<Site> sites = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("Site.findAll");
		sites = query.getResultList();

		em.getTransaction().commit();
		em.close();

		return sites;
	}
	
	@PUT
	@Path("/site/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site>  updateSite(@PathParam("id") int siteId, Site site) {
		List<Site> sites = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();

		Site siteToupdate = em.find(Site.class, siteId);
		siteToupdate.setLatitude(site.getLatitude());
		siteToupdate.setLatitude(site.getLatitude());
		siteToupdate.setName(site.getName());
		siteToupdate.setTowers(site.getTowers());
		em.merge(siteToupdate);
		
		Query query = em.createNamedQuery("Site.findAll");
		sites = query.getResultList();
		
		em.getTransaction().commit();
		em.close();

		return sites;
	}
	
	@POST
	@Path("/site")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site) {
		List<Site> sites = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(site);
		Query query = em.createNamedQuery("Site.findAll");
		sites = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return sites;
	}
	
	@DELETE
	@Path("/site/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> removeSite(@PathParam("id") int siteId) {
		List<Site> sites = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Site site=em.find(Site.class, siteId);
		em.remove(site);
		
		Query query = em.createNamedQuery("Site.findAll");
		sites = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return sites;
		
	}
	
//	public void exportSiteListToXmlFile(SiteList siteList, String xmlFileName){
//		File xmlFile = new File(xmlFileName);
//		try {
//			JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
//			Marshaller marshaller = jaxb.createMarshaller();
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			marshaller.marshal(siteList, xmlFile);
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void convertXmlFileToOutputFile(
//			String inputXmlFileName, 
//			String outputXmlFileName, 
//			String xsltFileName)
//	{
//		File inputXmlFile = new File(inputXmlFileName);
//		File outputXmlFile = new File(outputXmlFileName);
//		File xsltFile = new File(xsltFileName);
//		
//		StreamSource source = new StreamSource(inputXmlFile);
//		StreamSource xslt   = new StreamSource(xsltFile);
//		StreamResult output = new StreamResult(outputXmlFile);
//		
//		TransformerFactory factory = TransformerFactory.newInstance();
//		try {
//			Transformer transformer = factory.newTransformer(xslt);
//			transformer.transform(source, output);
//		} catch (TransformerConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TransformerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
//
//	public static void main(String[] args) {
//		SiteDao dao = new SiteDao();
//		
//		List<Site> sites = dao.findAllSites();
//		for(Site st:sites){
//			System.out.println(st.getName());	
//		}
//		
//	SiteList sitelist = new SiteList();
//	sitelist.setSites(sites);
//	dao.exportSiteListToXmlFile(sitelist, "xml/sites2.xml");
//		
//    	dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html","xml/sites2html.xslt");
//		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html","xml/sites2equipment.xslt");
//		// TODO Auto-generated method stub
//
//	}

}

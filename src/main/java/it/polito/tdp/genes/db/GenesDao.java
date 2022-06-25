package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {
	
	public void getVertici(Map<String, Genes> idMap){
		String sql = "SELECT DISTINCT * "
				+ "FROM genes "
				+ "WHERE Essential='Essential'";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if(!idMap.containsKey(res.getString("GeneID"))) {
					Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
					
					idMap.put(res.getString("GeneID"), genes);
				}
			}
			res.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getAdiacenze1(Map<String, Genes> idMap, List<Adiacenza> adiacenze){
		String sql = "SELECT DISTINCT i.GeneID1, i.GeneID2, ABS(Expression_Corr) AS peso "
				+ "FROM interactions i, genes g1, genes g2 "
				+ "WHERE GeneID1<>GeneID2 AND g2.GeneID=i.GeneID1 AND g1.GeneID=i.GeneID2 "
				+ "AND g1.Essential='Essential' AND g2.Essential='Essential' AND g1.Chromosome<>g2.Chromosome";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				adiacenze.add(new Adiacenza(idMap.get(res.getString("GeneID1")),
								idMap.get(res.getString("GeneID2")),
								res.getDouble("peso")));
			}
			res.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getAdiacenze2(Map<String, Genes> idMap, List<Adiacenza> adiacenze){
		String sql = "SELECT DISTINCT i.GeneID1, i.GeneID2, 2*ABS(Expression_Corr) AS peso "
				+ "FROM interactions i, genes g1, genes g2 "
				+ "WHERE GeneID1<>GeneID2 AND g2.GeneID=i.GeneID1 AND g1.GeneID=i.GeneID2 "
				+ "AND g1.Essential='Essential' AND g2.Essential='Essential' AND g1.Chromosome=g2.Chromosome";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				adiacenze.add(new Adiacenza(idMap.get(res.getString("GeneID1")),
								idMap.get(res.getString("GeneID2")),
								res.getDouble("peso")));
			}
			res.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

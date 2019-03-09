<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.Statement"%>
<%@ page import = "java.sql.SQLException"%>
<%@ page import = "java.text.SimpleDateFormat"%>
<%@ page import = "java.util.Date"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Cadastro de Medicao GPS</title>
	</head>
	<body>
		<%
			try
			{
				SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				String dispositivo = request.getParameter("dis");
				Date dataHora = formatoData.parse(String.valueOf(request.getParameter("dth")));
				float latitude = Float.parseFloat(request.getParameter("lat"));
				String orientacaoLatitude = request.getParameter("ola");
				float longitude = Float.parseFloat(request.getParameter("lon"));
				String orientacaoLongitude = request.getParameter("ola");
				float velocidade = Float.parseFloat(request.getParameter("vel"));

				Class.forName("com.mysql.jdbc.Driver").newInstance(); 
				Connection conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1/MRVBIB", "coletor", "ifes2017");
				Statement consulta = conexao.createStatement();
				consulta.execute("INSERT INTO DADO_GPS (DADO_CD_MAC_DISPOSITIVO, DADO_VL_LATITUDE, DADO_SG_ORIENTACAO_LATITUDE, DADO_VL_LONGITUDE, DADO_SG_ORIENTACAO_LONGITUDE, DADO_VL_VELOCIDADE, DADO_DT_CAPTURA, DADO_DT_REGISTRO) VALUES ('" + dispositivo + "', " + latitude + ", '" + orientacaoLatitude + "', " + longitude + ", '" + orientacaoLongitude + "', " + velocidade + ", '" + dataHora + "', NOW());");
				conexao.close();
			}
			catch (SQLException excecao)
			{
				out.println("Erro ao inserir novo registro: " + excecao);
			}
		%>
	</body>
</html>

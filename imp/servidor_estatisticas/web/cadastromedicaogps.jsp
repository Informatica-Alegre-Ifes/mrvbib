<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.Statement"%>
<%@ page import = "java.sql.SQLException"%>

<html>
	<head>
		<title>Cadastro de Medicao GPS</title>
	</head>
	<body>
		<%
			try
			{
				float latitude = Float.parseFloat(request.getParameter("latitude"));
				float longitude = Float.parseFloat(request.getParameter("longitude"));
				Class.forName("com.mysql.jdbc.Driver").newInstance(); 
				Connection conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1/MRVBIB", "coletor", "ifes2017");
				Statement consulta = conexao.createStatement();
				consulta.execute("INSERT INTO DADO_GPS (DADO_CD_MAC_DISPOSITIVO, DADO_VL_LATITUDE, DADO_SG_ORIENTACAO_LATITUDE, DADO_VL_LONGITUDE, DADO_SG_ORIENTACAO_LONGITUDE, DADO_VL_VELOCIDADE, DADO_DT_CAPTURA, DADO_DT_REGISTRO) VALUES ("HTTP TESTE", " + latitude + ", "W", " + latitude + ", "S", 0, NOW(), NOW());");
				conexao.close();
			}
			catch (SQLException excecao)
			{
				out.println("Erro ao inserir novo registro: " + excecao);
			}
		%>
	</body>
</html>
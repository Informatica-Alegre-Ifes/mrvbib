<?php
	// Obtém os valores enviado pelo leitor via HTTP Request (GET Method).
	$dispositivo = $_GET["dis"];
	$dataHora = date('Y-m-d H:i:s', strtotime($_GET["dth"]));
	$latitude = $_GET["lat"];
	$orientacaoLatitude = $_GET["ola"];
	$longitude = $_GET["lon"];
	$orientacaoLongitude = $_GET["olo"];
	$velocidade = $_GET["vel"];

	// Cria uma conexão com o SGDB MySQL, utilizando a API MySQLi.
	$conexao = new mysqli("127.0.0.1", "coletor", "ifes2017", "MRVBIB");

	// Verifica se ocorreram erros durante a criação da conexão.
	if (mysqli_connect_errno())
	{
		printf("Não foi possível conectar-se ao SGDB MySQL: %s\n", mysqli_connect_error());
		exit();
	}
	
	// Cria uma transação SQL.
	$transacao = $conexao->prepare("INSERT INTO DADO_GPS (DADO_CD_MAC_DISPOSITIVO, DADO_VL_LATITUDE, DADO_SG_ORIENTACAO_LATITUDE, DADO_VL_LONGITUDE, DADO_SG_ORIENTACAO_LONGITUDE, DADO_VL_VELOCIDADE, DADO_DT_CAPTURA, DADO_DT_REGISTRO) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())");

	// Associa, no script de transação SQL, os valores armazenados em variáveis de acordo
	$transacao->bind_param('sdsdsds', $dispositivo, $latitude, $orientacaoLatitude, $longitude, $orientacaoLongitude, $velocidade, $dataHora);

	// Executa a transação SQL.
	$transacao->execute();

	echo $transacao->error;

	// Fecha a conexão com o SGDB.
	$conexao->close();
?>
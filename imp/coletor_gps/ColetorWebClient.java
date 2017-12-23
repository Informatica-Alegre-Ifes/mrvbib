package coletor_gps;

import java.util.List;

class ColetorWebService
{
	public ColetorWebService()
	{
	}

	public boolean carregar()
	{
		List<Dado> dados = new Dado().listar();
	}
}
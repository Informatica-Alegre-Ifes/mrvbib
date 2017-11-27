package coletor_gps

class ColetorWebService
{
	public ColetorWebService()
	{
	}

	public boolean carregar()
	{
		List<Dado> dados = new Dado().listar();
	}

	public boolean ehIntranet()
	{
		ConnectivityManager conectivtyManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);  
if (conectivtyManager.getActiveNetworkInfo() != null  
    && conectivtyManager.getActiveNetworkInfo().isAvailable()  
    && conectivtyManager.getActiveNetworkInfo().isConnected()) {  
    isConnected = true;  
} else {  
    isConnected= false;  
}  
	}
}
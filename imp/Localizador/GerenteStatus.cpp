#include "GerenteStatus.h"

GerenteStatus* GerenteStatus::instancia = 0;

GerenteStatus::GerenteStatus() {
}

GerenteStatus *
GerenteStatus::obterInstancia()
{
        if (instancia == 0 )
                instancia = new GerenteStatus();
        return (instancia);
}

void
GerenteStatus::atualizarStatusGlobal(IStatusProdutor &produtor) {
  
}

void
GerenteStatus::adicionar(IStatusProdutor &produtor) {
        produtores.push_back(produtor);
}

void
GerenteStatus::notificar(Semaforo semaforo) {
        for (IStatusProdutor& produtor : produtores)
                produtor.statusMudou(semaforo);
}

#ifndef GERENTESTATUS_H
#define GERENTESTATUS_H

#include <vector>
#include <functional>
#include "IStatusProdutor.h"
#include "IStatusConsumidor.h"

class GerenteStatus : public IStatusConsumidor
{
public:
        GerenteStatus();

        static GerenteStatus *obterInstancia();
        void atualizarStatusGlobal(IStatusProdutor &produtor);
        void adicionar(IStatusProdutor &produtor) override;
        void notificar(Semaforo semaforo) override;

private:
        static GerenteStatus *instancia;
        std::vector<std::reference_wrapper<IStatusProdutor>> produtores;
        Semaforo semaforoStatusGlobal;
};

#endif

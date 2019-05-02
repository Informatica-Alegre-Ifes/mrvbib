#ifndef GERENTESTATUS_H
#define GERENTESTATUS_H

#include <memory>

class GerenteStatus
{
public:
        GerenteStatus();

        static GerenteStatus &obterInstancia();

private:
        std::unique_ptr<GerenteStatus> instancia;
};

#endif

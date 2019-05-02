#include "GerenteStatus.h"

GerenteStatus::GerenteStatus() {
}

GerenteStatus &
GerenteStatus::getInstance()
{
        if (instancia.get() == 0 )
        {
                instancia.reset(new GerenteStatus());
        }
        return *instancia;
}

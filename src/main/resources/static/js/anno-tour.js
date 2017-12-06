var annoTour = function(name){
  switch (name){
      case 'home':
          var anno_home = new Anno([{
                  target  : '#left-sidebar-nav',
                  position: 'right',
                  content : "Nos encontramos en la pantalla Principal, puedes acceder a las diferentes pantallas del sistema mediante los siguientes links."
              },
              {
                  target  : '#table-datatables',
                  position: 'center-bottom',
                  content : "Aquí puedes ver los socios que ingresaron al establecimiento en las últimas dos horas."
              },
              {
                  target  : '#fab',
                  position: 'left',
                  content : "Con el botón rojo puedes cargar el ingreso de un nuevo socio al establecimiento mientras que con el amarillo una nueva suscripción."
              }]);

          setTimeout(function(){
              anno_home.show();
          }, 2000);
          break;
      case 'customers':
          var anno_subscriptions = new Anno([{
                  target  : '#left-sidebar-nav',
                  position: 'right',
                  content : "Nos encontramos en la pantalla Principal, puedes acceder a las diferentes pantallas del sistema mediante los siguientes links."
              },
              {
                  target  : '#tbl-subscriptions',
                  position: 'center-bottom',
                  content : "Aquí puedes ver los socios que tienes registrados en tu establecimiento."
              },
              {
                  target  : '#fab',
                  position: 'left',
                  content : "Con el botón rojo puedes cargar el ingreso de un nuevo socio al establecimiento mientras que con el amarillo una nueva suscripción."
              }]);

          setTimeout(function(){
              anno_subscriptions.show();
          }, 2000);
          break;
  }
};
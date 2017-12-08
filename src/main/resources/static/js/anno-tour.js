let annoTour = function(name){
  switch (name){
      case 'home':
          let anno_home = new Anno([{
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
          let anno_subscriptions = new Anno([{
                  target  : '#tbl-subscriptions',
                  position: 'center-bottom',
                  content : "Aquí puedes ver las suscripciones actuales en tu establecimiento."
              }]);

          setTimeout(function(){
              anno_subscriptions.show();
          }, 2000);
          break;
      case 'new-entry':
          let anno_new_entry = new Anno([{
                  target  : '#login-page',
                  position: 'right',
                  content : "Aqui puedes cargar un nuevo ingreso al establecimiento con el dni de un socio. El sistema verificará que dicho socio tenga una suscripción válida. (Puedes intentarlo con el DNI 12345678)"
              }]);

          setTimeout(function(){
              anno_new_entry.show();
          }, 2000);
          break;
      case 'activities-stats':
          let anno_stats_activities = new Anno([{
                  target  : '#stats-activities',
                  position: 'left',
                  content : "Este gráfico muestra las suscripciones registradas durante el último año."
              },
              {
                  target  : '#select-activity',
                  position: 'center-bottom',
                  content : "Puedes filtrar por actividad."
              }]);

          setTimeout(function(){
              anno_stats_activities.show();
          }, 2000);
          break;
      case 'customers-stats':
          let anno_stats_customers = new Anno([{
                  target  : '#stats-customers',
                  position: 'left',
                  content : "Este gráfico muestra las de los socios del establecimiento."
              }]);

          setTimeout(function(){
              anno_stats_customers.show();
          }, 2000);
          break;
      case 'entries-stats':
          let anno_stats_entries = new Anno([{
                  target  : '#stats-entries-week',
                  position: 'center-bottom',
                  content : "Este gráfico muestra los ingresos de socios en la última semana."
              },
              {
                  target  : '#stats-entries-month',
                  position: 'center-top',
                  content : "Este último gráfico detalla los ingresos de socios en el mes actual."
              },
              {
                  target  : '#select-month',
                  position: 'center-top',
                  content : "También puedes filtrar por mes."
              }]);

          setTimeout(function(){
              anno_stats_entries.show();
          }, 2000);
          break;
      case 'balance':
          let anno_balance = new Anno([{
                  target  : '#tbl-balance',
                  position: 'center-bottom',
                  content : "Esta tabla representa la caja del establecimiento. Las suscripciones serán cargadas como Ingresos."
              },
              {
                  target  : '#card-incomes',
                  position: 'center-top',
                  content : "También puedes agregar ingresos externos sobre los cuales quieras llevar registro."
              },
              {
                  target  : '#card-expenses',
                  position: 'center-top',
                  content : "Así como también egresos de dinero. Puedes agregar o quitar Conceptos en la pestaña Ajustes."
              }]);

          setTimeout(function(){
              anno_balance.show();
          }, 2000);
          break;
      case 'staff':
          let anno_staff = new Anno([{
                  target  : '#tbl-admins',
                  position: 'center-bottom',
                  content : "En esta tabla se detallan los administradores del establecimiento. Un administrador es el único capaz de editar o eliminar su cuenta."
              },
              {
                  target  : '#tbl-employees',
                  position: 'center-top',
                  content : "En ésta tabla se muestran los empleados. Un administrador puede tanto editar como eliminar registros de empleados."
              },
              {
                  target  : '#div-new-staff',
                  position: 'center-top',
                  content : "Aquí puedes cargar administradores y empleados del establecimiento. Los Empleados tienen un nivel de acceso menor, puedes crear uno y luego loguearte para comprobarlo."
              }]);

          setTimeout(function(){
              anno_staff.show();
          }, 2000);
          break;
      case 'settings':
          let anno_settings = new Anno([{
                  target  : '#section-activities',
                  position: 'center-bottom',
                  content : "Aquí puedes ver las Actividades disponibles para que los socios se suscriban en tu establecimiento. Puedes crear nuevas, editar o eliminar las existentes."
              },
              {
                  target  : '#section-concepts',
                  position: 'center-top',
                  content : "Mientras que aquí puedes ver los Conceptos que son los que tienen un gasto/ingreso asociado en la pestaña Caja. Puedes agregar nuevos, editar o eliminar los existentes."
              }]);

          setTimeout(function(){
              anno_settings.show();
          }, 2000);
          break;
      case 'new-customer':
          let anno_new_customer = new Anno([{
                  target  : '#div-new-customer',
                  position: 'center-right',
                  content : "En éste formulario puedes cargar un nuevo socio en tu establecimiento."
              },
              {
                  target  : '#div-new-subscription',
                  position: 'center-left',
                  content : "Mientras que aquí puedes cargar una nueva suscripción a un socio a partir de su DNI, previamente debes haber cargado al menos una Actividad en la pestaña Ajustes."
              }]);

          setTimeout(function(){
              anno_new_customer.show();
          }, 2000);
          break;
      case 'customer-profile':
          let anno_customer_profile = new Anno([{
                  target  : '#profile-page-header',
                  position: 'center-bottom',
                  content : "En esta sección puedes ver la información básica del socio."
              },
              {
                  target  : '#profile-subscription',
                  position: 'center-right',
                  content : "Aquí puedes ver un detalle del tipo de suscripción que tiene el socio actualmente."
              },
              {
                  target  : '#profile-location',
                  position: 'center-right',
                  content : "Mientras que aquí un pequeño mapa que detalla su domicilio."
              },
              {
                  target  : '#profile-page-wall-share',
                  position: 'center-left',
                  content : "En ésta tabla se muestra un detalle de los ingresos al establecimiento del socio."
              },
              {
                  target  : '#profile-page-wall-posts',
                  position: 'center-left',
                  content : "Por último, en este campo se puede enviar un correo electrónico que el socio recibira en su casilla de correo."
              }]);

          setTimeout(function(){
              anno_customer_profile.show();
          }, 2000);
          break;
  }
};
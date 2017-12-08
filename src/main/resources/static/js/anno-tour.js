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
                  content : "Aqui puedes cargar un nuevo ingreso al establecimiento con el dni de un socio. El sistema verificará que dicho socio tenga una suscripción válida"
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
  }
};
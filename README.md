# k8s-microservices-routing-poc

Prueba de concepto con microservicios en Kubernetes donde se quieren evaluar los siguientes escenarios:
1. Microservicio que expone capcidades a otros microservicios dentro del cluster. Este consumo se realiza a traves de ALB, se quiere probar el consumo intracluster sin pasar por el ALB, para esto se expone el microservicio a traves de un servicio de tipo ClusterIP y se consume a traves del DNS del servicio. Evaluar mejoras en latencia, redimiento y costos.
2. El microservicio tiene diferentes deployments, donde cada uno responde a un endpoint diferente, se requiere evaluar la exposicion de un endpoint unico y por medio de DestinationRule y VirtualService de Istio enrutar el trafico a cada deployment dependiendo de etiquetas del microservicio consumidor.

Notas: Deben convivir los 2 escenarios.
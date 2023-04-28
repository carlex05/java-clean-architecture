# Clean Architecture Example with Java

This repository contains an example of Clean Architecture with Java. This architecture can be easily visualized by the separation of 5 main layers that are shown below, from the most stable to the least stable:

1. Model layer: this layer contains the structure of the information that will be handled at the business logic level. It is also possible to create a model layer for other layers, but I usually recommend keeping it simple and understandable as much as possible. I usually put the models with the name of the entity it represents in a package called "model".

2. Use case layer: here we will put the business logic of our microservice. It is important to emphasize that we should use the least amount of libraries or keep this part of our microservice as simple as possible, so we can easily extend it over time. The package name I usually give is "service", and the classes usually have "Service" as a suffix.

3. Data layer: the data repositories are an important point to develop the business logic of our services. It is a point of contact with other systems, so here we apply an inversion of dependencies with the ports and adapters pattern through the use of interfaces and implementation of the interfaces. The interface should be agnostic to the database being used (if it is relational or a plain file, it should not matter at this point), while the implementation should be isolated from the business logic and injected from the Main layer. I usually leave them in the "repository" package and add "Repository" as a suffix.

4. Controller layer: this layer is used to interact with the microservice, and infrastructure topics are detailed here, such as the protocol (REST, SOAP, queues) and type (synchronous or asynchronous) of communication. This layer depends heavily on the framework we use.

5. Main layer: I called it Main, but basically, it is a layer where we will host all the configuration that our microservice requires to function correctly.

![PriceHistory-Página-2](https://user-images.githubusercontent.com/60867448/235045868-7240570d-1ede-45e6-a143-398b119c44ec.jpg)

It is important to remember that the image above aims to illustrate that each circle represents a layer, and that the inner layers should not be aware of the existence of the outer layers. There should be no dependency towards a higher-level layer (less stable), whereas the outer layers do depend on the central ones.

The following diagram provides a clearer depiction of the dependency relationship between each package and how they are distributed among the components of the microservice.
![PriceHistory-Packages](https://user-images.githubusercontent.com/60867448/235048550-ae499a8c-6c56-4718-8fdc-4717ec88150d.jpg)


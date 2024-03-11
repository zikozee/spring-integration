# Spring Integration work

- **@MessagingGateway**: This annotation tells Spring integration to generate an implementation of this interface
  - at runtime- similar to how Spring Data automatically generates implementations of repository interfaces.
    - other parts of the code will use this interface when they need to write to a file

- **defaultRequestChannel**: attribute of **@MessagingGateway** indicates that any messages resulting from a call
  - to the interface methods should be sent to the given message channel.

- @Header annotation indicates that the value passed to filename should be placed in a message header 
  - (specified as FileHeaders.FILENAME, which is a constant in the FileHeaders
    - class that is equal to the value "file_name") rather than in the **message payload**
    - i.e header :::--> file_name=some_value
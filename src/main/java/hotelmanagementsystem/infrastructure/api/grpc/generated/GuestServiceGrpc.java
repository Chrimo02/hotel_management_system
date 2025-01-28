package hotelmanagementsystem.infrastructure.api.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Guest Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.66.0)",
    comments = "Source: api.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GuestServiceGrpc {

  private GuestServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "hotelmanagement.GuestService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getCreateGuestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateGuest",
      requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest.class,
      responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getCreateGuestMethod() {
    io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getCreateGuestMethod;
    if ((getCreateGuestMethod = GuestServiceGrpc.getCreateGuestMethod) == null) {
      synchronized (GuestServiceGrpc.class) {
        if ((getCreateGuestMethod = GuestServiceGrpc.getCreateGuestMethod) == null) {
          GuestServiceGrpc.getCreateGuestMethod = getCreateGuestMethod =
              io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateGuest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GuestServiceMethodDescriptorSupplier("CreateGuest"))
              .build();
        }
      }
    }
    return getCreateGuestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getGetGuestByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetGuestById",
      requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest.class,
      responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getGetGuestByIdMethod() {
    io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getGetGuestByIdMethod;
    if ((getGetGuestByIdMethod = GuestServiceGrpc.getGetGuestByIdMethod) == null) {
      synchronized (GuestServiceGrpc.class) {
        if ((getGetGuestByIdMethod = GuestServiceGrpc.getGetGuestByIdMethod) == null) {
          GuestServiceGrpc.getGetGuestByIdMethod = getGetGuestByIdMethod =
              io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetGuestById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GuestServiceMethodDescriptorSupplier("GetGuestById"))
              .build();
        }
      }
    }
    return getGetGuestByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getUpdateGuestEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateGuestEmail",
      requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest.class,
      responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getUpdateGuestEmailMethod() {
    io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getUpdateGuestEmailMethod;
    if ((getUpdateGuestEmailMethod = GuestServiceGrpc.getUpdateGuestEmailMethod) == null) {
      synchronized (GuestServiceGrpc.class) {
        if ((getUpdateGuestEmailMethod = GuestServiceGrpc.getUpdateGuestEmailMethod) == null) {
          GuestServiceGrpc.getUpdateGuestEmailMethod = getUpdateGuestEmailMethod =
              io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateGuestEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GuestServiceMethodDescriptorSupplier("UpdateGuestEmail"))
              .build();
        }
      }
    }
    return getUpdateGuestEmailMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getUpdateGuestPhoneMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateGuestPhone",
      requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest.class,
      responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getUpdateGuestPhoneMethod() {
    io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getUpdateGuestPhoneMethod;
    if ((getUpdateGuestPhoneMethod = GuestServiceGrpc.getUpdateGuestPhoneMethod) == null) {
      synchronized (GuestServiceGrpc.class) {
        if ((getUpdateGuestPhoneMethod = GuestServiceGrpc.getUpdateGuestPhoneMethod) == null) {
          GuestServiceGrpc.getUpdateGuestPhoneMethod = getUpdateGuestPhoneMethod =
              io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateGuestPhone"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GuestServiceMethodDescriptorSupplier("UpdateGuestPhone"))
              .build();
        }
      }
    }
    return getUpdateGuestPhoneMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getUpdateGuestLastNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateGuestLastName",
      requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest.class,
      responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getUpdateGuestLastNameMethod() {
    io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getUpdateGuestLastNameMethod;
    if ((getUpdateGuestLastNameMethod = GuestServiceGrpc.getUpdateGuestLastNameMethod) == null) {
      synchronized (GuestServiceGrpc.class) {
        if ((getUpdateGuestLastNameMethod = GuestServiceGrpc.getUpdateGuestLastNameMethod) == null) {
          GuestServiceGrpc.getUpdateGuestLastNameMethod = getUpdateGuestLastNameMethod =
              io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateGuestLastName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GuestServiceMethodDescriptorSupplier("UpdateGuestLastName"))
              .build();
        }
      }
    }
    return getUpdateGuestLastNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getUpdateGuestTitleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateGuestTitle",
      requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest.class,
      responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getUpdateGuestTitleMethod() {
    io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getUpdateGuestTitleMethod;
    if ((getUpdateGuestTitleMethod = GuestServiceGrpc.getUpdateGuestTitleMethod) == null) {
      synchronized (GuestServiceGrpc.class) {
        if ((getUpdateGuestTitleMethod = GuestServiceGrpc.getUpdateGuestTitleMethod) == null) {
          GuestServiceGrpc.getUpdateGuestTitleMethod = getUpdateGuestTitleMethod =
              io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateGuestTitle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GuestServiceMethodDescriptorSupplier("UpdateGuestTitle"))
              .build();
        }
      }
    }
    return getUpdateGuestTitleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getDeleteGuestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteGuest",
      requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest.class,
      responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getDeleteGuestMethod() {
    io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getDeleteGuestMethod;
    if ((getDeleteGuestMethod = GuestServiceGrpc.getDeleteGuestMethod) == null) {
      synchronized (GuestServiceGrpc.class) {
        if ((getDeleteGuestMethod = GuestServiceGrpc.getDeleteGuestMethod) == null) {
          GuestServiceGrpc.getDeleteGuestMethod = getDeleteGuestMethod =
              io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteGuest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new GuestServiceMethodDescriptorSupplier("DeleteGuest"))
              .build();
        }
      }
    }
    return getDeleteGuestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse> getGetBookingsByGuestIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBookingsByGuestId",
      requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest.class,
      responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest,
      hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse> getGetBookingsByGuestIdMethod() {
    io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse> getGetBookingsByGuestIdMethod;
    if ((getGetBookingsByGuestIdMethod = GuestServiceGrpc.getGetBookingsByGuestIdMethod) == null) {
      synchronized (GuestServiceGrpc.class) {
        if ((getGetBookingsByGuestIdMethod = GuestServiceGrpc.getGetBookingsByGuestIdMethod) == null) {
          GuestServiceGrpc.getGetBookingsByGuestIdMethod = getGetBookingsByGuestIdMethod =
              io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBookingsByGuestId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GuestServiceMethodDescriptorSupplier("GetBookingsByGuestId"))
              .build();
        }
      }
    }
    return getGetBookingsByGuestIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GuestServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GuestServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GuestServiceStub>() {
        @java.lang.Override
        public GuestServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GuestServiceStub(channel, callOptions);
        }
      };
    return GuestServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GuestServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GuestServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GuestServiceBlockingStub>() {
        @java.lang.Override
        public GuestServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GuestServiceBlockingStub(channel, callOptions);
        }
      };
    return GuestServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GuestServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GuestServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GuestServiceFutureStub>() {
        @java.lang.Override
        public GuestServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GuestServiceFutureStub(channel, callOptions);
        }
      };
    return GuestServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Guest Service
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void createGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateGuestMethod(), responseObserver);
    }

    /**
     */
    default void getGuestById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetGuestByIdMethod(), responseObserver);
    }

    /**
     */
    default void updateGuestEmail(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateGuestEmailMethod(), responseObserver);
    }

    /**
     */
    default void updateGuestPhone(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateGuestPhoneMethod(), responseObserver);
    }

    /**
     */
    default void updateGuestLastName(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateGuestLastNameMethod(), responseObserver);
    }

    /**
     */
    default void updateGuestTitle(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateGuestTitleMethod(), responseObserver);
    }

    /**
     */
    default void deleteGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteGuestMethod(), responseObserver);
    }

    /**
     */
    default void getBookingsByGuestId(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBookingsByGuestIdMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GuestService.
   * <pre>
   * Guest Service
   * </pre>
   */
  public static abstract class GuestServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GuestServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GuestService.
   * <pre>
   * Guest Service
   * </pre>
   */
  public static final class GuestServiceStub
      extends io.grpc.stub.AbstractAsyncStub<GuestServiceStub> {
    private GuestServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GuestServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GuestServiceStub(channel, callOptions);
    }

    /**
     */
    public void createGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateGuestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getGuestById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetGuestByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateGuestEmail(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateGuestEmailMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateGuestPhone(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateGuestPhoneMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateGuestLastName(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateGuestLastNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateGuestTitle(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateGuestTitleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteGuestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBookingsByGuestId(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest request,
        io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBookingsByGuestIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GuestService.
   * <pre>
   * Guest Service
   * </pre>
   */
  public static final class GuestServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GuestServiceBlockingStub> {
    private GuestServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GuestServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GuestServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse createGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateGuestMethod(), getCallOptions(), request);
    }

    /**
     */
    public hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse getGuestById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetGuestByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse updateGuestEmail(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateGuestEmailMethod(), getCallOptions(), request);
    }

    /**
     */
    public hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse updateGuestPhone(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateGuestPhoneMethod(), getCallOptions(), request);
    }

    /**
     */
    public hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse updateGuestLastName(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateGuestLastNameMethod(), getCallOptions(), request);
    }

    /**
     */
    public hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse updateGuestTitle(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateGuestTitleMethod(), getCallOptions(), request);
    }

    /**
     */
    public hotelmanagementsystem.infrastructure.api.grpc.generated.Empty deleteGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteGuestMethod(), getCallOptions(), request);
    }

    /**
     */
    public hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse getBookingsByGuestId(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBookingsByGuestIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GuestService.
   * <pre>
   * Guest Service
   * </pre>
   */
  public static final class GuestServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<GuestServiceFutureStub> {
    private GuestServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GuestServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GuestServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> createGuest(
        hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateGuestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getGuestById(
        hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetGuestByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestEmail(
        hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateGuestEmailMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestPhone(
        hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateGuestPhoneMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestLastName(
        hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateGuestLastNameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestTitle(
        hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateGuestTitleMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> deleteGuest(
        hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteGuestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse> getBookingsByGuestId(
        hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBookingsByGuestIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_GUEST = 0;
  private static final int METHODID_GET_GUEST_BY_ID = 1;
  private static final int METHODID_UPDATE_GUEST_EMAIL = 2;
  private static final int METHODID_UPDATE_GUEST_PHONE = 3;
  private static final int METHODID_UPDATE_GUEST_LAST_NAME = 4;
  private static final int METHODID_UPDATE_GUEST_TITLE = 5;
  private static final int METHODID_DELETE_GUEST = 6;
  private static final int METHODID_GET_BOOKINGS_BY_GUEST_ID = 7;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_GUEST:
          serviceImpl.createGuest((hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest) request,
              (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>) responseObserver);
          break;
        case METHODID_GET_GUEST_BY_ID:
          serviceImpl.getGuestById((hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest) request,
              (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>) responseObserver);
          break;
        case METHODID_UPDATE_GUEST_EMAIL:
          serviceImpl.updateGuestEmail((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest) request,
              (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>) responseObserver);
          break;
        case METHODID_UPDATE_GUEST_PHONE:
          serviceImpl.updateGuestPhone((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest) request,
              (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>) responseObserver);
          break;
        case METHODID_UPDATE_GUEST_LAST_NAME:
          serviceImpl.updateGuestLastName((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest) request,
              (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>) responseObserver);
          break;
        case METHODID_UPDATE_GUEST_TITLE:
          serviceImpl.updateGuestTitle((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest) request,
              (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>) responseObserver);
          break;
        case METHODID_DELETE_GUEST:
          serviceImpl.deleteGuest((hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest) request,
              (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver);
          break;
        case METHODID_GET_BOOKINGS_BY_GUEST_ID:
          serviceImpl.getBookingsByGuestId((hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest) request,
              (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateGuestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest,
              hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>(
                service, METHODID_CREATE_GUEST)))
        .addMethod(
          getGetGuestByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest,
              hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>(
                service, METHODID_GET_GUEST_BY_ID)))
        .addMethod(
          getUpdateGuestEmailMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest,
              hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>(
                service, METHODID_UPDATE_GUEST_EMAIL)))
        .addMethod(
          getUpdateGuestPhoneMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest,
              hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>(
                service, METHODID_UPDATE_GUEST_PHONE)))
        .addMethod(
          getUpdateGuestLastNameMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest,
              hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>(
                service, METHODID_UPDATE_GUEST_LAST_NAME)))
        .addMethod(
          getUpdateGuestTitleMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest,
              hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>(
                service, METHODID_UPDATE_GUEST_TITLE)))
        .addMethod(
          getDeleteGuestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest,
              hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(
                service, METHODID_DELETE_GUEST)))
        .addMethod(
          getGetBookingsByGuestIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest,
              hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse>(
                service, METHODID_GET_BOOKINGS_BY_GUEST_ID)))
        .build();
  }

  private static abstract class GuestServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GuestServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GuestService");
    }
  }

  private static final class GuestServiceFileDescriptorSupplier
      extends GuestServiceBaseDescriptorSupplier {
    GuestServiceFileDescriptorSupplier() {}
  }

  private static final class GuestServiceMethodDescriptorSupplier
      extends GuestServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    GuestServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GuestServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GuestServiceFileDescriptorSupplier())
              .addMethod(getCreateGuestMethod())
              .addMethod(getGetGuestByIdMethod())
              .addMethod(getUpdateGuestEmailMethod())
              .addMethod(getUpdateGuestPhoneMethod())
              .addMethod(getUpdateGuestLastNameMethod())
              .addMethod(getUpdateGuestTitleMethod())
              .addMethod(getDeleteGuestMethod())
              .addMethod(getGetBookingsByGuestIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}

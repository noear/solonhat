package org.noear.solonhat.smartdoc;

public interface SolonMvcAnnotations {
    String REQUEST_MAPPING = "Mapping";
    String REQUEST_MAPPING_FULLY = "org.noear.solon.annotation.Mapping";

    String GET_MAPPING = "Get";
    String GET_MAPPING_FULLY = "org.noear.solon.annotation.Get";

    String POST_MAPPING = "Post";
    String POST_MAPPING_FULLY = "org.noear.solon.annotation.Post";
    String PUT_MAPPING = "Put";
    String PUT_MAPPING_FULLY = "org.noear.solon.annotation.Put";
    String PATCH_MAPPING = "Patch";
    String PATCH_MAPPING_FULLY = "org.noear.solon.annotation.Patch";
    String DELETE_MAPPING = "Delete";
    String DELETE_MAPPING_FULLY = "org.noear.solon.annotation.Delete";

    String REQUEST_PARAM = "Param";
    String REQUEST_BODY = "Body";
    String CONTROLLER = "Controller";
}

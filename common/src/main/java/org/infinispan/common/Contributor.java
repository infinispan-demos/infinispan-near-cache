package org.infinispan.common;

import org.infinispan.protostream.annotations.Proto;

@Proto
public record Contributor(int code, String name) {
}

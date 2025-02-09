/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.celeborn.common.network.protocol;

import io.netty.buffer.ByteBuf;

import org.apache.celeborn.common.protocol.PbBufferStreamEnd;

public class BufferStreamEnd extends RequestMessage {
  private long streamId;

  public BufferStreamEnd(long streamId) {
    this.streamId = streamId;
  }

  @Override
  public int encodedLength() {
    return 8;
  }

  @Override
  public void encode(ByteBuf buf) {
    buf.writeLong(streamId);
  }

  @Override
  public Type type() {
    return Type.BUFFER_STREAM_END;
  }

  public static Message decode(ByteBuf buffer) {
    long streamId = buffer.readLong();
    return new BufferStreamEnd(streamId);
  }

  public long getStreamId() {
    return streamId;
  }

  public static BufferStreamEnd fromProto(PbBufferStreamEnd pb) {
    return new BufferStreamEnd(pb.getStreamId());
  }
}

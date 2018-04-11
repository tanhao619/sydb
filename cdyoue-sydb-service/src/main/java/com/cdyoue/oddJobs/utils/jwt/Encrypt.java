/*
 * FileName: Encrypt.java
 * Copyright (C) 2016 youedata Tech. Co. Ltd. All Rights Reserved <admin@youedata.com>
 * 
 * Licensed under the youedata License, Version 1.1 (the "License");
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * author  : shenmingzhou <admin@youedata.com>
 * date     : 2016年2月24日 下午2:53:23
 * last modify author :
 * version : 1.1
 */
package com.cdyoue.oddJobs.utils.jwt;

import org.apache.commons.codec.digest.DigestUtils;

import java.nio.channels.SeekableByteChannel;
import java.util.UUID;

//加final是 checkStyle验证  防止实例化工具类
public final class Encrypt {
	// 防止实例化工具类
	private Encrypt() {
	}

	public static String getMd5(String originString) {
		return DigestUtils.md5Hex(originString);
	}

		public static void main(String[] args) {
            String uuid = UUID.randomUUID().toString();
            System.err.println(uuid);
			System.out.println(getMd5("123456"+uuid));
		}
}

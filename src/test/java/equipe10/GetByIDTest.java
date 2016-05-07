/*
 * Copyright 2016 ibirajara.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package equipe10;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author ibirajara
 */
public class GetByIDTest extends BaseTest{
    
    @Test
    @Ignore
    public void index() throws Exception {
      server.get("/")
          .expect(200)
          .header("Content-Type", "text/html;charset=UTF-8");
    }
    @Test
    public void getById() throws Exception{
        System.out.println("Entrou no teste por id...");
        App a = new App();
        a.addUsertoList();
        String json = "{\n1\n}";
        server.get("/todos")
        .expect(200);
        //System.out.println(json);

    }
}

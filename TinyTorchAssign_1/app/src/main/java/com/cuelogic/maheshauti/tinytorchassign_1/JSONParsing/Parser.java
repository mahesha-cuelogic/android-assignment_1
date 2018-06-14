package com.cuelogic.maheshauti.tinytorchassign_1.JSONParsing;

import com.cuelogic.maheshauti.tinytorchassign_1.Adapter.TinyTorchPostAdapter;
import com.cuelogic.maheshauti.tinytorchassign_1.model.PostDetails;
import com.cuelogic.maheshauti.tinytorchassign_1.utils.Keys;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maheshauti on 28/03/18.
 */

public class Parser {
    ArrayList<PostDetails> list;
    JSONObject jsonObject;
    String JsonObjectString;
    public Parser(ArrayList<PostDetails> list,String jsonObjectString){
        this.JsonObjectString=jsonObjectString;
        this.list=list;
    }
    public void parseJsonString()
    {

        try {
            /**
             * Check Whether Its NULL???
             */


                 jsonObject = new JSONObject(JsonObjectString);


                System.out.println("data recieved is " + jsonObject);

                JSONObject metaObject=jsonObject.getJSONObject("meta");
                String nextpage=metaObject.getString("next_page");
            System.out.println("next page is ==="+nextpage);
            if(!nextpage.equals("null"))
            {

                TinyTorchPostAdapter.isLastPage=false;
            }
            else
            {  System.out.println("true value returned");
                TinyTorchPostAdapter.isLastPage=true;
            }

                JSONArray array = jsonObject.getJSONArray(Keys.DATA);
                int lenArray = array.length();
                System.out.println("length of the array is :" + lenArray);

                if (lenArray > 0) {
                    for (int jIndex = 0; jIndex < lenArray; jIndex++) {

                            /*
                            * creating every time new object and adding into the list*/
                        PostDetails model = new PostDetails();

                        JSONObject innerObject = array.getJSONObject(jIndex);
                     //   String tags = innerObject.getString(Keys.TAGS);
                        String tags="";
                        try {
                            JSONArray tagJson=(JSONArray) innerObject.getJSONArray(Keys.TAGS);
                            if(tagJson!=null){

                                for (int j = 0; j < tagJson.length(); j++)
                                {
                                    String tagobj = (String) tagJson.get(j);
                                    if(tags.equals(""))
                                    {
                                        tags = tags +""+ tagobj;
                                    }
                                    else
                                    {
                                        tags = tags +" ,"+ tagobj;
                                    }

                                }
                            }



                        }catch (Exception e)
                        {
                            System.out.println("tags array found null");
                        }

                        String msg = innerObject.getString(Keys.msg);
                        model.setMsg(msg);
                        model.setTag(tags);


                        //  @SuppressLint({"NewApi", "LocalSuppress"})

                        if (innerObject.has(Keys.ATTACHMENTS)) {

                            JSONArray AttachmentArry = innerObject.getJSONArray(Keys.ATTACHMENTS);
                            JSONObject innerAtttachmentObject = AttachmentArry.getJSONObject(0);
                            String imgUrl = innerAtttachmentObject.getString(Keys.IMGURL);
                            model.setImgUrl(imgUrl);
                        } else {
                            model.setImgUrl(null);
                        }


                        list.add(model);


                }
            }

        } catch (Exception e) {
            System.out.println("failed to recieved data" + e);
        }


    }
}

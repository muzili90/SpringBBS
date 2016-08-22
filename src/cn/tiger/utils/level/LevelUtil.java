package cn.tiger.utils.level;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.tiger.entity.bbs.CommunityLevel;


public class LevelUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			CommunityLevel bbsLevel1=new CommunityLevel();
			bbsLevel1.setId(1L);
			bbsLevel1.setExperience(0L);
			
			CommunityLevel bbsLevel2=new CommunityLevel();
			bbsLevel2.setId(2L);
			bbsLevel2.setExperience(30L);
			
			CommunityLevel bbsLevel3=new CommunityLevel();
			bbsLevel3.setId(3L);
			bbsLevel3.setExperience(100L);
			
			CommunityLevel bbsLevel4=new CommunityLevel();
			bbsLevel4.setId(4L);
			bbsLevel4.setExperience(290L);
			
			CommunityLevel bbsLevel5=new CommunityLevel();
			bbsLevel5.setId(5L);
			bbsLevel5.setExperience(600L);
			
			CommunityLevel bbsLevel6=new CommunityLevel();
			bbsLevel6.setId(6L);
			bbsLevel6.setExperience(1050L);
			
			CommunityLevel bbsLevel7=new CommunityLevel();
			bbsLevel7.setId(7L);
			bbsLevel7.setExperience(1650L);
			
			CommunityLevel bbsLevel8=new CommunityLevel();
			bbsLevel8.setId(8L);
			bbsLevel8.setExperience(2650L);
			
			CommunityLevel bbsLevel9=new CommunityLevel();
			bbsLevel9.setId(9L);
			bbsLevel9.setExperience(7650L);
			
			ArrayList<CommunityLevel> list=new ArrayList<CommunityLevel>();
			list.add(bbsLevel1);
			list.add(bbsLevel2);
			list.add(bbsLevel3);
			list.add(bbsLevel4);
			list.add(bbsLevel5);
			list.add(bbsLevel6);
			list.add(bbsLevel7);
			list.add(bbsLevel8);
			list.add(bbsLevel9);
			
			
			getNewLevel(list,30L);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static CommunityLevel getNewLevel(List<CommunityLevel> bbsLevels,long experience) throws Exception{
		if(bbsLevels.size()!=9){
			throw new Exception("数组传入错误");
		}
		int index=0;
		CommunityLevel bbsLevelResult=null;
		
		long[] level=new long[9];
		int levelIndex=0;
		Iterator<CommunityLevel> it=bbsLevels.iterator();
		while(it.hasNext()){
			CommunityLevel temp=it.next();
			System.out.println("id="+temp.getId());
			level[levelIndex++]=temp.getExperience();
		}
		
		/*level[0]=0;
		level[1]=30;
		level[2]=100;
		level[3]=290;
		level[4]=600;
		level[5]=1050;
		level[6]=1650;
		level[7]=2650;
		level[8]=7650;*/
		
		//set 检测 负数修改为0 整数不能大过9999
		
		display(level);
		
		if(experience<0){
			index=0;
			bbsLevelResult=bbsLevels.get(index++);
			System.out.println(bbsLevelResult.getId()+" "+bbsLevelResult.getExperience());
			return bbsLevelResult;
		}else if(experience==0){
			index=0;
			bbsLevelResult=bbsLevels.get(index++);
			System.out.println(bbsLevelResult.getId()+" "+bbsLevelResult.getExperience());
			return bbsLevelResult;
		}else{
			for(int i=0;i<level.length-1;i++){
				if(level[i]>=experience){
					index=i;
					break;
				}
			}
			
			if(index==0){
				index=level.length-1;
			}
			
			if(experience<level[index]){
				System.out.println("index="+(--index));
			}else if(experience==level[index]){
				System.out.println("index="+index);
			}else{
				System.out.println("index="+index);
			}
			
			bbsLevelResult=bbsLevels.get(index++);
			System.out.println(bbsLevelResult.getId()+" "+bbsLevelResult.getExperience());
			return bbsLevelResult;
		}

	}
	
	public static void display(long[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}

}

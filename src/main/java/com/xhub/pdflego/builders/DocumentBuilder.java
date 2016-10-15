package com.xhub.pdflego.builders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xhub.pdflego.bloc.PLTextBlock;
import com.xhub.pdflego.core.*;
import org.apache.log4j.Logger;
import java.util.*;

/**
 * Created by amine
 */
public class DocumentBuilder {
    private Logger logger = Logger.getLogger(DocumentBuilder.class);
    private Map<String, Class> componentBuildMap;
    private List<String> compositeTypes;

    public DocumentBuilder(){
        componentBuildMap = new LinkedHashMap<String, Class>(){{
            put("PLTextBlock", PLTextBlock.class);
            put("HorizontalLayout", HorizontalLayout.class);
            put("VerticalLayout", VerticalLayout.class);
            put("HorizontalGridLayout", HorizontalGridLayout.class);
            put("VerticalGridLayout", VerticalGridLayout.class);
        }};
        compositeTypes = Arrays.asList("HorizontalLayout", "VerticalLayout", "HorizontalGridLayout", "VerticalGridLayout");
    }

    public Composite build(JsonNode jsonNode){
        Composite rootComponent = null;
        try{
            rootComponent = (Composite) this.buildComponent(jsonNode);
        }catch(Exception e){
            logger.error("could not build Document", e);
        }
        return rootComponent;
    }

    private Component buildComponent(JsonNode jsonNode){
        String className = jsonNode.get("className").textValue();
        Component result = null;
        if(compositeTypes.contains(className)){
            result = buildComposite(jsonNode);
        }else if(componentBuildMap.containsKey(className)){
            result = buildBlock(jsonNode);
        }
        return result;
    }

    private Component buildBlock(JsonNode jsonNode){
        String className = jsonNode.get("className").textValue();
        Component result = null;
        try{
            Class<? extends Component> type = componentBuildMap.get(className);
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.treeToValue(jsonNode, type);
            return result;
        }catch(Exception e){
            logger.error("couldn't build Block of type " + className, e);
        }
        return result;
    }

    private Component buildComposite(JsonNode jsonNode){
        Composite result = (Composite) buildBlock(jsonNode);
        JsonNode childrenNode = jsonNode.get("children");
        List<Component> children = new ArrayList<>();
        for(JsonNode node:childrenNode){
            children.add(this.buildComponent(node));
        }
        if(result != null){
            children.stream().forEach(component -> component.setParent(result));
            result.setChildren(children);
        }
        return result;
    }
}

package com.bordeaux.entity.pert;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GraphToDot {

	private StringBuilder dot;
	private Map<String, String> nodeMap = new HashMap<String, String>();
	private Map<String, String> relationMap = new HashMap<String, String>();

	public GraphToDot() {
		dot = new StringBuilder();
	}

	public void addRelation(String x, String y, Task task, Edge edge) {

		String entry = null;

		if (task != null) {

			if (task.getName().contains("dotted")) {
				entry = x + " -> " + y + "[style=dotted]" + ";\n";
			} else {
				entry = x + " -> " + y + "[label=\"" + task.getDuration() + " " + task.getName() + "\"]" + ";\n";
			}

			if (!task.getName().equals("end")) {

				relationMap.put(x + y, entry);
				addNode(x, edge.getLeft());
				addNode(y, edge.getRight());

			}

		}

	}

	private void addNode(String name, Node node) {
		nodeMap.put(name,
				name + "[label= \"" 
						+ node.getNum() 
						+ " [" 
						+ String.format("%.1f", node.getTot()) 
						+ ", "
						+ String.format("%.1f", node.getTard())
						+ "]"+
						"\", shape=\"egg\",style=\"filled\", color=\"black\", fillcolor=\"#efc94c\"];\n");
	}

	public String getDot() {
		dot.append("digraph mon_graphe {rankdir=\"LR\";\n");
		for (Entry<String, String> entry : nodeMap.entrySet()) {
			dot.append(entry.getValue());
		}

		for (Entry<String, String> entry : relationMap.entrySet()) {
			dot.append(entry.getValue());
		}

		dot.append("}");

		return dot.toString();
	}
}

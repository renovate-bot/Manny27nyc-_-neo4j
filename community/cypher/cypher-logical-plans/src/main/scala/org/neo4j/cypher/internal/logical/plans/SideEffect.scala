/*
 * Copyright (c) "Neo4j"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.cypher.internal.logical.plans

import org.neo4j.cypher.internal.expressions.Expression
import org.neo4j.cypher.internal.expressions.LabelName
import org.neo4j.cypher.internal.expressions.PropertyKeyName
import org.neo4j.cypher.internal.expressions.RelTypeName
import org.neo4j.cypher.internal.expressions.SemanticDirection

sealed trait SideEffect

sealed trait CreateSideEffect extends SideEffect

sealed trait SetSideEffect extends SideEffect

case class CreateNodeSideEffect(node: String,
                                labels: Seq[LabelName],
                                properties: Option[Expression]) extends CreateSideEffect

case class CreatRelationshipSideEffect(relationship: String,
                                       startNode: String,
                                       typ: RelTypeName,
                                       endNode: String,
                                       direction: SemanticDirection,
                                       properties: Option[Expression]) extends CreateSideEffect

case class SetLabelsSideEffect(node: String,
                               labelNames: Seq[LabelName]) extends SetSideEffect

case class SetNodePropertySideEffect(node: String,
                                     propertyKey: PropertyKeyName,
                                     value: Expression) extends SetSideEffect

case class SetRelationshipPropertySideEffect(relationship: String,
                                             propertyKey: PropertyKeyName,
                                             value: Expression) extends SetSideEffect

INSERT INTO Distributor.Sites(id, code, name)
	SELECT id, code, name FROM ac1.Sites;

INSERT INTO Distributor.StreetFrames(id, siteId, name)
	SELECT id, siteId, name FROM ac1.StreetFrames;
    
INSERT INTO Distributor.StreetCables(id, name, streetFrameId)
	SELECT id, name, frameId FROM ac1.StreetCables;

INSERT INTO Distributor.StreetPairs(id, pair, streetCableId)
	SELECT id, pair, cableId FROM ac1.StreetPairs;

INSERT INTO Distributor.SwitchBlocks(id, name)
	SELECT id, name FROM ac1.SwitchBlocks;

INSERT INTO Distributor.BlockPositions(id, position, switchBlockId)
	SELECT id, position, switchBlockId FROM ac1.BlockPositions;

INSERT INTO Distributor.SigmaLineEquipments(id, tsw, khw, phw, `row`, `column`, blockPositionId)
	(SELECT ac1.NEAX61Sigma.id, ac1.NEAX61Sigma.timeSwitch, ac1.NEAX61Sigma.kHighway, ac1.NEAX61Sigma.pHighway, ac1.NEAX61Sigma.row, ac1.NEAX61Sigma.column, ac1.BlockPositions.id
		FROM ac1.NEAX61Sigma
			LEFT JOIN ac1.Distributor ON ac1.Distributor.neax61sigmaId = ac1.NEAX61Sigma.id
            LEFT JOIN ac1.BlockPositions ON ac1.BlockPositions.id = ac1.Distributor.blockPositionId);
    
INSERT INTO Distributor.ELineEquipments(id, spce, shw, hw, gr, sw, lvl, blockPositionId)
	(SELECT ac1.NEAX61E.id, ac1.NEAX61E.spce, ac1.NEAX61E.highway, ac1.NEAX61E.subhighway, ac1.NEAX61E.group, ac1.NEAX61E.switch, ac1.NEAX61E.level, ac1.BlockPositions.id
		FROM ac1.NEAX61E
			LEFT JOIN ac1.Distributor ON ac1.Distributor.neax61eId = ac1.NEAX61E.id
            LEFT JOIN ac1.BlockPositions ON ac1.BlockPositions.id = ac1.Distributor.blockPositionId);
    
INSERT INTO Distributor.SigmaELUs(id, name)
	SELECT id, name FROM ac1.NEAX61SigmaELUs;

INSERT INTO Distributor.SigmaELUEquipments(id, eluId, l3addr, blockPositionId)
	(SELECT ac1.SigmaL3Addr.id, ac1.SigmaL3Addr.eluId, ac1.SigmaL3Addr.l3addr, ac1.BlockPositions.id
		FROM ac1.SigmaL3Addr
			LEFT JOIN ac1.Distributor ON ac1.Distributor.sigmal3addrId = ac1.SigmaL3Addr.id
            LEFT JOIN ac1.BlockPositions ON ac1.BlockPositions.id = ac1.Distributor.blockPositionId);

INSERT INTO Distributor.ZhoneEquipments(id, cable, port, blockPositionId)
	(SELECT ac1.Zhone.id, ac1.Zhone.cable, ac1.Zhone.port, ac1.BlockPositions.id
		FROM ac1.Zhone
			LEFT JOIN ac1.Distributor ON ac1.Distributor.zhoneId = ac1.Zhone.id
            LEFT JOIN ac1.BlockPositions ON ac1.BlockPositions.id = ac1.Distributor.blockPositionId);


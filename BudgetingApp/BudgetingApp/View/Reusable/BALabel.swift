//
//  BALabel.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 09.07.2022.
//

import UIKit

class BALabel: UILabel {

	fileprivate func configure() {
		textColor = .systemGray
		font = .boldSystemFont(ofSize: 24)
		translatesAutoresizingMaskIntoConstraints = false
	}
	
	override init(frame: CGRect) {
		super.init(frame: frame)
		configure()
	}
	
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	
	init(text: String) {
		super.init(frame: .zero)
		self.text = text
		configure()
	}
}

//
//  BACollectionViewCell.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 12.07.2022.
//

import UIKit

class BACollectionViewCell: UICollectionViewCell {
	
	static let cellID = "cellID"
	
	let sectionImageView = UIImageView()
	let sectionTitleLabel = UILabel()
	let sectionBonusLabel = UILabel()
	
	func setup(title: String, cash: String, imageTitle: String) {
		sectionTitleLabel.text = title
		sectionBonusLabel.text = cash
		sectionImageView.image = UIImage(systemName: imageTitle)
	}
	
	override init(frame: CGRect) {
		super.init(frame: frame)
		configure()
	}
	
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	
	func configure() {
		sectionImageView.translatesAutoresizingMaskIntoConstraints = false
		sectionTitleLabel.translatesAutoresizingMaskIntoConstraints = false
		sectionBonusLabel.translatesAutoresizingMaskIntoConstraints = false
		
		sectionTitleLabel.textAlignment = .center
		sectionTitleLabel.font = .systemFont(ofSize: 12)
		sectionBonusLabel.textAlignment = .center
		sectionBonusLabel.font = .systemFont(ofSize: 10
		)
		
		backgroundColor = .systemOrange
		layer.masksToBounds = true
		layer.cornerRadius = 30
		
		addSubview(sectionImageView)
		addSubview(sectionTitleLabel)
		addSubview(sectionBonusLabel)
		let padding: CGFloat = 10
		NSLayoutConstraint.activate([
			sectionImageView.topAnchor.constraint(equalTo: topAnchor, constant: padding),
			sectionImageView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: padding),
			sectionImageView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -padding),
			sectionImageView.heightAnchor.constraint(equalTo: sectionImageView.widthAnchor),
			
			sectionBonusLabel.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -1),
			sectionBonusLabel.leadingAnchor.constraint(equalTo: sectionImageView.leadingAnchor),
			sectionBonusLabel.trailingAnchor.constraint(equalTo: sectionImageView.trailingAnchor),
			sectionBonusLabel.heightAnchor.constraint(equalToConstant: 8),
			
			sectionTitleLabel.topAnchor.constraint(equalTo: sectionImageView.bottomAnchor, constant: 1),
			sectionTitleLabel.bottomAnchor.constraint(equalTo: sectionBonusLabel.topAnchor, constant: 1),
			sectionTitleLabel.leadingAnchor.constraint(equalTo: sectionImageView.leadingAnchor),
			sectionTitleLabel.trailingAnchor.constraint(equalTo: sectionImageView.trailingAnchor)
		])
	}
}

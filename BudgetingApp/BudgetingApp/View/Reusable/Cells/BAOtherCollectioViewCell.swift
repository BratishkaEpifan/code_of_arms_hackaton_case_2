//
//  BAOtherCollectioViewCell.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 13.07.2022.
//

import UIKit

class BAOtherCollectionViewCell: UICollectionViewCell {
    
    static let cellID = "cellID"
    
    let sectionImageView = UIImageView()
    let sectionTitleLabel = UILabel()
    let sectionBonusLabel = UILabel()
    let sectionPotBonusLabel = UILabel()
    
    func setup(_ category: (String, String, String, String)) {
        sectionTitleLabel.text = category.0
        NetworkManager.shared.getPurchases(path: category.3) { [weak self] result in
            switch result {
            case .success(let cat):
                self?.sectionBonusLabel.text = "\(Double(Int(100*cat.potentialBonus))/100) B"
                self?.sectionPotBonusLabel.text = "\(Double(Int(100*cat.bonus))/100) B"
            case .failure(_): print("Error occured")
            }
        }
//        sectionBonusLabel.text = "+\(category.expense) B"
        let image = UIImage(named: category.1)
        sectionImageView.image = image
        sectionImageView.tintColor = UIColor(named: category.2)
        sectionBonusLabel.textColor = .systemRed
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .white
        configure()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func configure() {
        sectionImageView.translatesAutoresizingMaskIntoConstraints = false
        sectionTitleLabel.translatesAutoresizingMaskIntoConstraints = false
        sectionBonusLabel.translatesAutoresizingMaskIntoConstraints = false
        sectionPotBonusLabel.translatesAutoresizingMaskIntoConstraints = false
        
        sectionTitleLabel.textAlignment = .center
        sectionTitleLabel.font = .systemFont(ofSize: 16)
        sectionTitleLabel.numberOfLines = 0
        sectionBonusLabel.textAlignment = .center
        sectionBonusLabel.font = .systemFont(ofSize: 14)
        sectionPotBonusLabel.textAlignment = .center
        sectionPotBonusLabel.font = .systemFont(ofSize: 14)
        sectionPotBonusLabel.textColor = .systemGreen
        
        layer.masksToBounds = true
        layer.cornerRadius = 20
        
        addSubview(sectionImageView)
        addSubview(sectionTitleLabel)
        addSubview(sectionBonusLabel)
        addSubview(sectionPotBonusLabel)
        let padding: CGFloat = 20
        NSLayoutConstraint.activate([
            sectionImageView.topAnchor.constraint(equalTo: topAnchor, constant: 5),
            sectionImageView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: padding+20),
            sectionImageView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -padding-20),
            sectionImageView.heightAnchor.constraint(equalTo: sectionImageView.widthAnchor),
            
            sectionBonusLabel.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -5),
            sectionBonusLabel.leadingAnchor.constraint(equalTo: leadingAnchor),
            sectionBonusLabel.trailingAnchor.constraint(equalTo: trailingAnchor),
            sectionBonusLabel.heightAnchor.constraint(equalToConstant: 12),
            
            sectionPotBonusLabel.bottomAnchor.constraint(equalTo: sectionBonusLabel.topAnchor, constant: -5),
            sectionPotBonusLabel.leadingAnchor.constraint(equalTo: leadingAnchor),
            sectionPotBonusLabel.trailingAnchor.constraint(equalTo: trailingAnchor),
            sectionPotBonusLabel.heightAnchor.constraint(equalToConstant: 12),
            
            sectionTitleLabel.topAnchor.constraint(equalTo: sectionImageView.bottomAnchor),
            sectionTitleLabel.bottomAnchor.constraint(equalTo: sectionPotBonusLabel.topAnchor),
            sectionTitleLabel.leadingAnchor.constraint(equalTo: leadingAnchor),
            sectionTitleLabel.trailingAnchor.constraint(equalTo: trailingAnchor)
        ])
    }
}
